package cn.exrick.xboot.activiti.listener;


import cn.exrick.xboot.activiti.entity.business.Leave;
import cn.exrick.xboot.activiti.service.business.LeaveService;
import cn.exrick.xboot.core.common.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author Exrickx
 */
@Slf4j
public class MyTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {

        // 获取关联业务表ID变量(启动流程代码里已存入tableId，此处直接获取即可)
        String tableId = (String) delegateTask.getVariable("tableId");
        log.info(tableId);
        LeaveService leaveService = SpringContextUtil.getBean(LeaveService.class);
        Leave leave = leaveService.get(tableId);

        // 获取其他流程信息
        String procInsId = delegateTask.getProcessInstanceId();
        log.info(procInsId);
    }
}
