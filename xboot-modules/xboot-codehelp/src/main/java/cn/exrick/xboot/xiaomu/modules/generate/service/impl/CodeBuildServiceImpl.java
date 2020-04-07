/**
 * Copyright (C), 2019, 小木
 * FileName: CodeBuildServiceImpl
 * Author:   xiaomu
 * Date:     2019/10/22 0:10
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.modules.generate.service.impl;

import cn.exrick.xboot.xiaomu.common.constant.Fields;
import cn.exrick.xboot.xiaomu.common.utils.FreemarkerUtil;
import cn.exrick.xboot.xiaomu.common.utils.LocalFileUtil;
import cn.exrick.xboot.xiaomu.modules.datasource.model.dto.ColumnConfig;
import cn.exrick.xboot.xiaomu.modules.datasource.model.dto.TableConfig;
import cn.exrick.xboot.xiaomu.modules.datasource.service.ColumnConfigService;
import cn.exrick.xboot.xiaomu.modules.datasource.service.TableConfigService;
import cn.exrick.xboot.xiaomu.modules.generate.model.bo.BuildModel;
import cn.exrick.xboot.xiaomu.modules.generate.service.CodeBuildService;
import cn.exrick.xboot.xiaomu.modules.template.model.bo.NodeTemplate;
import cn.exrick.xboot.xiaomu.modules.template.model.bo.ResultNode;
import cn.exrick.xboot.xiaomu.modules.template.model.bo.TreeResultNode;
import cn.exrick.xboot.xiaomu.modules.template.service.TemplateGroupService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class CodeBuildServiceImpl implements CodeBuildService {

    private static final String CODE_DEFAULT_PATH = "/code";//代码下载默认文件夹

    @Autowired
    private TemplateGroupService templateGroupService;
    @Autowired
    private TableConfigService tableConfigService;
    @Autowired
    private ColumnConfigService columnConfigService;

    /**
     * 解析模板组
     * @param buildModel
     * @return
     */
    @Override
    public List<ResultNode> processTemplate(BuildModel buildModel){
        return processTemplate(buildModel, true);
    }

    @Override
    public void generate(BuildModel buildModel, HttpServletResponse httpServletResponse) {

        List<ResultNode> processedTemplateList = processTemplate(buildModel);
        for(ResultNode resultNode : processedTemplateList){
            if(resultNode.isFile()){
                FileUtil.writeUtf8String(resultNode.getContent(), CODE_DEFAULT_PATH+resultNode.getNodeRealPath());
            }else{
                FileUtil.mkdir(CODE_DEFAULT_PATH+resultNode.getNodeRealPath());
            }
        }

        LocalFileUtil.downloadPath(CODE_DEFAULT_PATH, httpServletResponse);
    }

    @Override
    public TreeResultNode getTreeResultNode(BuildModel buildModel){
        TreeResultNode rootNode = new TreeResultNode();//构造跟节点
        rootNode.setFile(false);
        rootNode.setNodeId(0);
        rootNode.setNodeName("code");
        rootNode.setNodePath(CODE_DEFAULT_PATH);
        rootNode.setNodeRealPath(CODE_DEFAULT_PATH);

        List<ResultNode> processedTemplateList = processTemplate(buildModel, false);
        List<TreeResultNode> rootNodeList = getRootNode(processedTemplateList);
        for(TreeResultNode treeResultNode : rootNodeList){
            treeResultNode.setChildList(getChildResultNode(treeResultNode.getNodePath(), processedTemplateList));
        }
        rootNode.setChildList(rootNodeList);

        return rootNode;
    }

    /**
     * 解析模板列表
     * @param buildModel
     * @param isProcessFileContent 是否解析文件内容
     * @return
     */
    private List<ResultNode> processTemplate(BuildModel buildModel, boolean isProcessFileContent){
        List<ResultNode> resultNodeList = new ArrayList<ResultNode>();
        if(StringUtils.isBlank(buildModel.getTableIds())) return resultNodeList;

        Collection<TableConfig> tableConfigCollection = tableConfigService.listByIds(Arrays.asList(buildModel.getTableIds().split(",")));
        ArrayList tableConfigList = new ArrayList(tableConfigCollection);

        List<NodeTemplate> nodeTemplateList = null;
        if(isProcessFileContent){
            nodeTemplateList = templateGroupService.getTreeTemplateList(buildModel.getGroupId());
        }else{
            nodeTemplateList = templateGroupService.getTreeTemplateListWithoutContent(buildModel.getGroupId());
        }

        for(NodeTemplate nodeTemplate : nodeTemplateList){
            List<ResultNode> processedNodeList = new ArrayList<ResultNode>();
            getResultNodeList(nodeTemplate, buildModel.getDataMap(),"", tableConfigList, processedNodeList);
            resultNodeList.addAll(processedNodeList);
        }

        return resultNodeList;
    }

    /**
     * 递归解析子列表
     * @param nodePath
     * @param resultNodeList
     * @return
     */
    private List<TreeResultNode> getChildResultNode(String nodePath, List<ResultNode> resultNodeList){
        List<TreeResultNode> childList = new ArrayList<>();
        for(ResultNode resultNode : resultNodeList){
            if(resultNode.getNodePath().startsWith(nodePath)
                && nodePath.split("/").length+1==resultNode.getNodePath().split("/").length){
                TreeResultNode tempNode = new TreeResultNode();
                BeanUtils.copyProperties(resultNode, tempNode);
                childList.add(tempNode);
            }
        }
        for(TreeResultNode childNode : childList){
            childNode.setChildList(getChildResultNode(childNode.getNodePath(), resultNodeList));
        }
        return childList;
    }

    /**
     * 查找根节点（以/开头，后面跟数字结尾的为跟节点）
     * @param nodeList
     * @return
     */
    private List<TreeResultNode> getRootNode(List<ResultNode> nodeList){
        List<TreeResultNode> rootList = new ArrayList<>();
        for(ResultNode resultNode : nodeList){
            if(Pattern.matches("^/\\d*", resultNode.getNodePath())){
                TreeResultNode tempNode = new TreeResultNode();
                BeanUtils.copyProperties(resultNode, tempNode);
                rootList.add(tempNode);
            }
        }
        return rootList;
    }



    /**
     * 获取单个模板渲染后的文件列表
     * @param nodeTemplate
     * @param templateData
     * @param currentPath
     * @param tableConfigList
     * @param resultNodeList
     */
    private void getResultNodeList(NodeTemplate nodeTemplate, Map<String, Object> templateData,
                                   String currentPath, List<TableConfig> tableConfigList, List<ResultNode> resultNodeList){

        currentPath += "/"+nodeTemplate.getNodeName();
        if(templateData == null) templateData = new HashMap<>();

        //模板名包含表名则表示要按照多个表数据循环解析
        if(nodeTemplate.getNodeName().contains(Fields.TEMPLATE_FIELD.TABLE)){
            for(TableConfig tableConfig : tableConfigList){
                templateData.put(Fields.TEMPLATE_FIELD.TABLE, getTableFields(tableConfig));

                processNode(currentPath, nodeTemplate, templateData, tableConfigList, resultNodeList);
            }
        }else{
            processNode(currentPath, nodeTemplate, templateData, tableConfigList, resultNodeList);
        }
    }

    /**
     * 获取表模板字段
     * @param tableConfig
     * @return
     */
    private Map<String, Object> getTableFields(TableConfig tableConfig){
        Map<String, Object> tableMap = new HashMap<>();
        tableMap.put(Fields.TEMPLATE_FIELD.TABLE_NAME, tableConfig.getTableName());
        tableMap.put(Fields.TEMPLATE_FIELD.TABLE_DESC, tableConfig.getDescription());

        tableMap.putAll(getColumnFields(tableConfig.getId()));
        return tableMap;
    }

    /**
     * 获取列模板字段
     * @param tableId
     * @return
     */
    private Map<String, Object> getColumnFields(Integer tableId){
        List<ColumnConfig> columnList = columnConfigService.getColumnByTableId(tableId);

        Map<String, Object> columnData = new HashMap<>();
        if(CollectionUtils.isEmpty(columnList)) return columnData;

        List<Map<String, Object>> columnDataList = new ArrayList<>();
        List<String> keyList = new ArrayList<>();
        for(ColumnConfig columnConfig : columnList){
            Map<String, Object> columnMap = new HashMap<>();;
            columnMap.put(Fields.TEMPLATE_FIELD.COLUMN_NAME, columnConfig.getColumnName());
            columnMap.put(Fields.TEMPLATE_FIELD.COLUMN_TYPE, columnConfig.getColumnType());
            columnMap.put(Fields.TEMPLATE_FIELD.COLUMN_LENGTH, columnConfig.getColumnLength());
            columnMap.put(Fields.TEMPLATE_FIELD.COLUMN_DESC, columnConfig.getDescription());
            columnMap.put(Fields.TEMPLATE_FIELD.IS_KEY, columnConfig.isKey());
            if(columnConfig.isKey()) keyList.add(columnConfig.getColumnName());
            columnDataList.add(columnMap);
        }
        columnData.put(Fields.TEMPLATE_FIELD.KEY_LIST, keyList);
        columnData.put(Fields.TEMPLATE_FIELD.COLUMN_LIST, columnDataList);
        return columnData;
    }

    /**
     * 解析节点
     * @param currentPath
     * @param nodeTemplate
     * @param templateData
     * @param tableConfigList
     * @param resultNodeList
     */
    private void processNode(String currentPath, NodeTemplate nodeTemplate, Map<String, Object> templateData,
                             List<TableConfig> tableConfigList, List<ResultNode> resultNodeList){
        List<NodeTemplate> nodeChildList = nodeTemplate.getChildList();

        ResultNode resultNode = new ResultNode();
        resultNode.setNodeId(nodeTemplate.getNodeId());
        resultNode.setNodeName(nodeTemplate.getNodeName());
        resultNode.setNodePath(nodeTemplate.getNodePath());
        resultNode.setNodeRealPath(FreemarkerUtil.process(currentPath, templateData));
        //无子节点且businId不为空表示节点为文件
        if(CollectionUtils.isEmpty(nodeChildList) && nodeTemplate.getBusinId() != null){
            resultNode.setFile(true);
            if(StringUtils.isNotBlank(nodeTemplate.getContent()))
                resultNode.setContent(FreemarkerUtil.process(nodeTemplate.getContent(), templateData));
        }else{
            //为目录，递归遍历
            for(NodeTemplate childNodeTemplate : nodeChildList){
                getResultNodeList(childNodeTemplate, templateData, FreemarkerUtil.process(currentPath, templateData), tableConfigList, resultNodeList);
            }
        }
        resultNodeList.add(resultNode);
    }



}
