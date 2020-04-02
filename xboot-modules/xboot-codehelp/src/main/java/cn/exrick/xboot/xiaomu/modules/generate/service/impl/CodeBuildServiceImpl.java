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
import cn.exrick.xboot.xiaomu.modules.template.service.TemplateGroupService;
import cn.hutool.core.io.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@Service
public class CodeBuildServiceImpl implements CodeBuildService {

    private static final String CODE_DEFAULT_PATH = "/code";

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
        List<ResultNode> resultNodeList = new ArrayList<ResultNode>();
        if(StringUtils.isBlank(buildModel.getTableIds())) return resultNodeList;

        Collection<TableConfig> tableConfigCollection = tableConfigService.listByIds(Arrays.asList(buildModel.getTableIds().split(",")));
        ArrayList tableConfigList = new ArrayList(tableConfigCollection);

        List<NodeTemplate> nodeTemplateList = templateGroupService.getTreeTemplatList(buildModel.getGroupId());
        for(NodeTemplate nodeTemplate : nodeTemplateList){
            List<ResultNode> processedNodeList = new ArrayList<ResultNode>();
            getResultNodeList(nodeTemplate, buildModel.getDataMap(),"", tableConfigList, processedNodeList);
            resultNodeList.addAll(processedNodeList);
        }

        return resultNodeList;
    }

    @Override
    public void generate(BuildModel buildModel, HttpServletResponse httpServletResponse) {
        List<File> tempFileList = new ArrayList<>();

        List<ResultNode> processedTemplateList = processTemplate(buildModel);
        for(ResultNode resultNode : processedTemplateList){
            tempFileList.add(FileUtil.writeUtf8String(resultNode.getContent(), CODE_DEFAULT_PATH+resultNode.getNodePath()));
        }

        LocalFileUtil.download(CODE_DEFAULT_PATH, httpServletResponse);
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

        List<NodeTemplate> nodeChildList = nodeTemplate.getChildList();

        //无子节点且businId不为空表示节点为文件
        if(CollectionUtils.isEmpty(nodeChildList) && nodeTemplate.getBusinId() != null){
            //模板名包含表名则表示要按照多个表数据循环解析
            if(nodeTemplate.getNodeName().contains(Fields.TEMPLATE_FIELD.TABLE)){
                for(TableConfig tableConfig : tableConfigList){
                    templateData.put(Fields.TEMPLATE_FIELD.TABLE, getTableFields(tableConfig));

                    ResultNode resultNode = new ResultNode();
                    resultNode.setNodeId(nodeTemplate.getNodeId());
                    resultNode.setNodeName(nodeTemplate.getNodeName());
                    resultNode.setFile(true);
                    resultNode.setNodePath(FreemarkerUtil.process(currentPath, templateData));
                    resultNode.setContent(FreemarkerUtil.process(nodeTemplate.getContent(), templateData));
                    resultNodeList.add(resultNode);
                }
            }else{
                ResultNode resultNode = new ResultNode();
                resultNode.setNodeId(nodeTemplate.getNodeId());
                resultNode.setNodeName(nodeTemplate.getNodeName());
                resultNode.setFile(true);
                resultNode.setNodePath(FreemarkerUtil.process(currentPath, templateData));
                resultNode.setContent(FreemarkerUtil.process(nodeTemplate.getContent(), templateData));
                resultNodeList.add(resultNode);
            }
        }else{
            //为目录，继续遍历
            for(NodeTemplate childNodeTemplate : nodeChildList){
                getResultNodeList(childNodeTemplate, templateData, FreemarkerUtil.process(currentPath, templateData), tableConfigList, resultNodeList);
            }
        }
    }






    /*************************************** 设置解析的字段名，方便统一修改 ******************************************************/
    public Map<String, Object> getTableFields(TableConfig tableConfig){
        Map<String, Object> tableMap = new HashMap<>();
        tableMap.put(Fields.TEMPLATE_FIELD.TABLE_NAME, tableConfig.getTableName());
        tableMap.put(Fields.TEMPLATE_FIELD.TABLE_DESC, tableConfig.getDescription());

        tableMap.putAll(getColumnFields(tableConfig.getId()));
        return tableMap;
    }

    public Map<String, Object> getColumnFields(Integer tableId){
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




}
