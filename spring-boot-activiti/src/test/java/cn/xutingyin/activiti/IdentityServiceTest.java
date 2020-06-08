package cn.xutingyin.activiti;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.test.ActivitiRule;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 身份管理服务测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IdentityServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdentityServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    public void testIdentity() {
        IdentityService identityService = activitiRule.getIdentityService(); // 这里不依赖流程部署文件，所以不用配置
        User user1 = identityService.newUser("user1"); // 初始化用户user1
        user1.setEmail("user1@111.com");
        User user2 = identityService.newUser("user2");
        user2.setEmail("user2@111.com");
        identityService.saveUser(user1); // 将用户保存
        identityService.saveUser(user2);

        Group group1 = identityService.newGroup("group1"); // 初始化用户组group1
        identityService.saveGroup(group1);
        Group group2 = identityService.newGroup("group2");
        identityService.saveGroup(group2);

        identityService.createMembership("user1", "group1"); // 构建用户与用户组的关系
        identityService.createMembership("user2", "group1");
        identityService.createMembership("user1", "group2");

        User user1_2 = identityService.createUserQuery().userId("user1").singleResult(); // 将用户名修改,版本号改为2
        user1_2.setLastName("user1_2");
        identityService.saveUser(user1_2);

        List<User> userList = identityService.createUserQuery().memberOfGroup("group1").listPage(0, 100);// 查询用户列表
        for (User user : userList) { // 查询group1组中的所有用户
            LOGGER.info("group1的user : [{}]", ToStringBuilder.reflectionToString(user, ToStringStyle.JSON_STYLE)); // 按照json格式输出
        }

        List<Group> groupList = identityService.createGroupQuery().groupMember("user1").listPage(0, 100);
        for (Group group : groupList) { // 查询user1用户所属的组
            LOGGER.info("user1的group : [{}]", ToStringBuilder.reflectionToString(group, ToStringStyle.JSON_STYLE));
        }
    }
}