package com.starforum.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Article implements Serializable {
    private String id;
    private String userId;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Integer isRecommend;
    private Integer visits;
    private Integer thumbup;
    private Integer comment;
    private Integer state;
    private String url;
    private String labelId;

    private List<Comment> comments;
}
