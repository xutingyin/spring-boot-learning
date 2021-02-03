package cn.xutingyin.springdatajpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import cn.xutingyin.springdatajpa.dao.UserRepository;
import cn.xutingyin.springdatajpa.entity.User;

@SpringBootTest
class SpringDataJpaApplicationTests {

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
        List<User> users = userRepository.findAll((Specification<User>)(root, query, cb) -> {
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
}
