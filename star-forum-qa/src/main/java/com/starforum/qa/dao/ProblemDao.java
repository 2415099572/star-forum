package com.starforum.qa.dao;

import com.starforum.qa.entity.Problem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface ProblemDao {
    @Results(id = "problem",
            value = {
                    @Result(id = true, column ="id" ,property = "id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "title", property = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "content", property = "content", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "createtime", property = "createTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "updatetime", property = "updateTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "userid", property = "userId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "nickname", property = "nickName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "visits", property = "visits", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "thumbup", property = "thumbup", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "reply", property = "reply", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            }
    )
    @Select("SELECT `id`, `title`, `content`, `createtime`, `updatetime`, `userid`, `nickname`, `visits`, `thumbup`, `reply` FROM tb_problem")
    List<Problem> findAllProblem();

    @Results(id = "problem1",
            value = {
                    @Result(id = true, column ="id" ,property = "id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "title", property = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "content", property = "content", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "createtime", property = "createTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "updatetime", property = "updateTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "userid", property = "userId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "nickname", property = "nickName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "visits", property = "visits", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "thumbup", property = "thumbup", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "reply", property = "reply", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "id", property = "replies", many = @Many(select = "com.starforum.qa.dao.ReplyDao.findReplyByProblemId", fetchType = FetchType.LAZY))
            }
    )
    @Select("SELECT `id`, `title`, `content`, `createtime`, `updatetime`, `userid`, `nickname`, `visits`, `thumbup`, `reply` FROM tb_problem WHERE id = #{problemId}")
    Problem findProblemById(@Param("problemId") String problemId);

    @ResultMap(value = "problem")
    @Select("SELECT `id`, `title`, `content`, `createtime`, `updatetime`, `userid`, `nickname`, `visits`, `thumbup`, `reply` FROM tb_problem ORDER BY visits DESC")
    List<Problem> findHotProblem();
}
