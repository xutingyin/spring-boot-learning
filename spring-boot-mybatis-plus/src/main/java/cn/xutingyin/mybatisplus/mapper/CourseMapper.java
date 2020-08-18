package cn.xutingyin.mybatisplus.mapper;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.xutingyin.mybatisplus.entity.Course;

/**
 * @Description: 利用Pagination 插件实现多表分页查询
 * @Author: xuty
 * @Date: 2019/11/11 16:32
 */
@Component
public interface CourseMapper extends BaseMapper<Course> {}
