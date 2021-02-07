package cn.xutingyin.springdatajpa;

import java.math.BigDecimal;
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
    @Test
    public void testFloat(){
        double d1 = 0.3d;
        double d2 = 0.1d;
        //输出结果： 0.19999999999999998
        /**
         * double类型 0.3-0.1的情况。需要将0.3转成二进制在运算
         * 【对于十进制的小数转换成二进制采用乘2取整法进行计算，取掉整数部分后，剩下的小数继续乘以2,直到小数部分全为0】
         * 0.3 * 2 = 0.6 => .0 (.6)取0剩0.6
         * 0.6 * 2 = 1.2 => .01 (.2)取1剩0.2
         * 0.2 * 2 = 0.4 => .010 (.4)取0剩0.4
         * 0.4 * 2 = 0.8 => .0100 (.8) 取0剩0.8
         * 0.8 * 2 = 1.6 => .01001 (.6)取1剩0.6
         * .............
         */
        System.out.println("0.3 - 0.1 = "+(d1 - d2));

        //----------------------------------------使用BigDecimal来解决精度丢失问题---------------------------
        BigDecimal b1 = new BigDecimal(d1);//会出现精度丢失
        BigDecimal b2 = new BigDecimal("0.3"); // 这种写法也是BigDecimal官方推荐写法，十进制整数在转换为二进制时不会出现精度丢失问题，那么把十进制小数扩大N倍让它在整数的维度上进行计算，并保留相应的精度信息。
        System.out.println(b1);
        System.out.println(b2);
    }
}
