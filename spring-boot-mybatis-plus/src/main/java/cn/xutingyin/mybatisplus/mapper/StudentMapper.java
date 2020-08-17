package cn.xutingyin.mybatisplus.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.xutingyin.mybatisplus.entity.Student;
import cn.xutingyin.mybatisplus.entity.StudentDto;

/**
 * @Description: 利用Pagination 插件实现多表分页查询
 * @Author: xuty
 * @Date: 2019/11/11 16:32
 */
@Mapper
public interface StudentMapper extends BaseMapper<StudentDto> {

    IPage<StudentDto> listStudentScore(Page<StudentDto> page);

    void addUser(Student student);
}
