package cn.xutingyin.springdatajpa.controller;

import cn.xutingyin.springdatajpa.dao.UserRepository;
import cn.xutingyin.springdatajpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userController")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("findAll")
    public List<User> findAll(){
        return  userRepository.findAll();
    }
    @GetMapping("findByNameLike")
    public List<User> findByNameLike(String name){
        return  userRepository.findByNameLike(name);
    }

    /**
     * 根据对象查询
     * @param name
     * @return
     */
    @GetMapping("findByNameLike2")
    public List<User> findByNameLike2(String name){
        return  userRepository.findByNameLike2(name);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("findByPage")
    public List<User> findByPage(int page,int size){
        PageRequest pageRequest = PageRequest.of(page -1, size, Sort.by(Sort.Order.desc("createTime")));
        Page<User> userPage = userRepository.findAll(pageRequest);
        return  userPage.getContent();
    }



}
