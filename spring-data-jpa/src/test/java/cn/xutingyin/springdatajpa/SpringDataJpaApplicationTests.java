package cn.xutingyin.springdatajpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import cn.xutingyin.springdatajpa.dao.UserRepository;
import cn.xutingyin.springdatajpa.entity.User;

@SpringBootTest
class SpringDataJpaApplicationTests {
    private Logger LOG = LoggerFactory.getLogger(SpringDataJpaApplicationTests.class);

    @Autowired
    private UserRepository userRepository;

    @Test
    void listAll() {
        List<User> all = userRepository.findAll();
        System.out.println(all);

    }

    @Test
    void saveEntity() {
        User user = new User();
        user.setName("李依依");
        user.setAge(20);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        User save = userRepository.save(user);
        System.out.println(save);
    }

    @Test
    void getById() {
        List<User> users = userRepository.findAllById(Lists.newArrayList("1", "2"));
        System.out.println(users);
    }

    @Test
    void listByCondition() {
        List<User> users = userRepository.findAll((Specification<User>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.like(root.get("name"), "李"));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        System.out.println(users);
    }

    @Test
    void findByNameLike() {
        String name = "李";
        // List<User> users = userRepository.findByNameLike(name);
        List<User> users2 = userRepository.findByNameLike2(name);
        // System.out.println(users);
        System.out.println(users2);
    }

    /**
     *  在循环内捕获异常 - 异常位置跳过，后面的会继续执行
     */
    @Test
    public void loopTryCatchTest(){
        Map<String, Object> a = new HashMap();
        a.put("a", "1");
        a.put("b", null);
        a.put("c", "3");
        for (Map.Entry<String, Object> moEntry : a.entrySet()) {
            try {
                boolean flag = moEntry.getValue().equals("1");
                System.out.println(moEntry.getKey() + "," + moEntry.getValue() + "," + flag);
            } catch (Exception e) {
                LOG.info("异常跳出，后面的循环还会继续执行{}",e.getMessage());
            }
        }

    }

    /**
     * 在循环外捕获异常 -- 异常后的循环内容则不会再执行
     */
    @Test
    public void tryCatchLoopTest(){
        Map<String, Object> a = new HashMap();
        a.put("a", "1");
        a.put("b", null);
        a.put("c", "3");
        try {
            for (Map.Entry<String, Object> moEntry : a.entrySet()) {
                boolean flag = moEntry.getValue().equals("1");
                System.out.println(moEntry.getKey() + "," + moEntry.getValue() + "," + flag);
            }
        } catch (Exception e) {
            LOG.info("异常跳出，循环后面的则不会再执行,{}", e.getMessage());
        }
    }
}
