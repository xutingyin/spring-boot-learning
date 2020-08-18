package cn.xutingyin.mybatisplus.vo;

import java.io.Serializable;
import java.util.List;

import cn.xutingyin.mybatisplus.dto.StudentDto;
import lombok.Data;

@Data
public class StudentVo implements Serializable {
    private List<StudentDto> students;
    private long currentPage;
    private long totalSize;
}
