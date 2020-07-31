package cn.xutingyin.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Storage implements Serializable {
    private Integer id;

    private String commodityCode;

    private Integer count;

}