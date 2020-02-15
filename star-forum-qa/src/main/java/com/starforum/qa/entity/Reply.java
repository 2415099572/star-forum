package com.starforum.qa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Reply implements Serializable {
    String id;
    String problemId;
    String content;
    Date createTime;
    Date updateTime;
    String userId;
    String nickName;
}
