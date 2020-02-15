package com.starforum.qa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Problem implements Serializable {
    String id;
    String title;
    String content;
    Date createTime;
    Date updateTime;
    String userId;
    String nickName;
    int visits;
    int thumbup;
    int reply;

    List<Reply> replies;
}
