package com.starforum.qa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class ProblemLabel implements Serializable {
    String problemId;
    String labelId;
}
