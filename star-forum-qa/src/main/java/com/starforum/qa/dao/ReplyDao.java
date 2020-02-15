package com.starforum.qa.dao;


import com.starforum.qa.entity.Reply;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface ReplyDao {
    @Results(id = "reply",
            value = {
                    @Result(id = true, column ="id" ,property = "id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "problemid", property = "problemId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "content", property = "content", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "createtime", property = "createTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "updatetime", property = "updateTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "userid", property = "userId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "nickname", property = "nickName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            }
    )
    @Select("SELECT `id`, `problemid`, `content`, `createtime`, `updatetime`, `userid`, `nickname` FROM tb_reply")
    List<Reply> findAllReply();

    @ResultMap(value = "reply")
    @Select("SELECT `id`, `problemid`, `content`, `createtime`, `updatetime`, `userid`, `nickname` FROM tb_reply WHERE problemid = #{problemId}")
    List<Reply> findReplyByProblemId(@Param("problemId") String problemId);
}
