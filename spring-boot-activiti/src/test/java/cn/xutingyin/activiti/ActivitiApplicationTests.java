package cn.xutingyin.activiti;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ActivitiApplicationTests {
    @Autowired
    /**
     * 这里单独使用@Autowired 时，会出现 Could not autowired, No been have beans of 'ProcessEngine' type found
     * 所以添加 @SuppressWarnings("all")，或者将@Autowired 换成 @Resource注解
     */
    @SuppressWarnings("all")
    private ProcessEngine engine;
    /**
     * 下面的所有Service 都可以通过 ProcessEngine 获取得到,例如：runtimeService = engine.getRuntimeService()，其它同理可得
     */

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private TaskService taskService;

    @Test
    void contextLoads() {
        RepositoryService repositoryService = engine.getRepositoryService();
        // 部署流程文件
        repositoryService.createDeployment().addClasspathResource("processes/activitiTest.bpmn").deploy();
        // 启动流程
        RuntimeService runtimeService = engine.getRuntimeService();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");
        // 查询第一个任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前节点名称:" + task.getName());
        // 完成第一个任务
        taskService.complete(task.getId());
        task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("流程结束后，查找任务：" + task);
        engine.close();
    }

    @Test
    void testProcess() {
        /*创建流程引擎*/
        RepositoryService repositoryService = engine.getRepositoryService();
        // 发布流程
        repositoryService.createDeployment().addClasspathResource("processes/activitiTest.bpmn").deploy();
        // 获取运行时服务
        RuntimeService runtimeService = engine.getRuntimeService();
        // 启动流程(myProcess 为bpmn中的id)
        ProcessInstance myProcess = runtimeService.startProcessInstanceByKey("myProcess");
        // 获取任务服务
        TaskService taskService = engine.getTaskService();

        // 员工提交申请
        Task task = taskService.createTaskQuery().processInstanceId(myProcess.getId()).singleResult();
        System.out.println("当前流程节点：" + task.getName()); // submit apply
        // 提交流程
        taskService.complete(task.getId());

        // 经理审批申请
        task = taskService.createTaskQuery().processInstanceId(myProcess.getId()).singleResult();
        System.out.println("当前流程节点：" + task.getName()); // check apply
        // 提交流程 此时该流程结束
        taskService.complete(task.getId());

        // 流程结束
        task = taskService.createTaskQuery().processInstanceId(myProcess.getId()).singleResult();
        System.out.println("流程结束后：" + task);// null

        // 关闭流程引擎
        engine.close();
    }
}
