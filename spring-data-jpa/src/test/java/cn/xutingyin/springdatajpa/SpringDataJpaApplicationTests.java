package cn.xutingyin.springdatajpa;

import cn.xutingyin.springdatajpa.dao.UserRepository;
import cn.xutingyin.springdatajpa.entity.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
    void saveEntity(){
        User user = new User();
        user.setName("赵武");
        User save = userRepository.save(user);
        System.out.println(save);
    }

    @Test
    void getById(){
        List<User> users = userRepository.findAllById(Lists.newArrayList("1", "2"));
        System.out.println(users);
    }



}
