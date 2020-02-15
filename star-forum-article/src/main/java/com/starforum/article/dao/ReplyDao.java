package com.starforum.article.dao;

import com.starforum.article.entity.Reply;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

@Mapper
public interface ReplyDao {

    @Results(id = "reply",
            value = {
                    @Result(id = true, column ="id" ,property = "id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column ="commentid" ,property = "commentId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column ="content" ,property = "content", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column ="createtime" ,property = "createTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column ="fromuid" ,property = "fromUid", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column ="touid" ,property = "toUid", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            }
    )
    @Select("SELECT `id`, `commentid`, `content`, `createtime`, `fromuid`, `touid` FROM tb_reply WHERE commentid = #{commentId}")
    public List<Reply> findReplyByCommentId(@Param("commentId") String commentId);
}
