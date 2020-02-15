package com.starforum.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Reply implements Serializable {
    private String id;
    private String commentId;
    private String content;
    private Date createTime;
    private String fromUid;
    private String toUid;
}
