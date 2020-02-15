package com.starforum.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Comment implements Serializable {
    private String id;
    private String articleId;
    private String content;
    private String uId;
    private Date createTime;
    private Integer thumbup;
    private Integer reply;

    private List<Reply> replys;
}
