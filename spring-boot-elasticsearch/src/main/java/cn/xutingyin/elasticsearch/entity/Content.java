package cn.xutingyin.elasticsearch.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Content implements Serializable {
    private String img;
    private String price;
    private String title;
}
