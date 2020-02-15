package com.starforum.article.dao;

import com.starforum.article.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

@Mapper
public interface CommentDao {

    @Results(id = "comment",
            value = {
                    @Result(id = true, column ="id" ,property = "id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column ="articleid" ,property = "articleId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column ="content" ,property = "content", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column ="uid" ,property = "uId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column ="createtime" ,property = "createTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column ="thumbup" ,property = "thumbup", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column ="reply" ,property = "reply", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "id", property = "replys", many = @Many(select = "com.starforum.article.dao.ReplyDao.findReplyByCommentId", fetchType = FetchType.LAZY))
            }
     )
    @Select("SELECT `id`, `articleid`, `content`, `uid`, `createtime`, `thumbup`, `reply` FROM tb_comment WHERE articleid = #{articleId}")
    public List<Comment> findCommentByArticleId(@Param("articleId") String articleId);
}
