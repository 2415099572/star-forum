package com.starforum.user.dao;

import com.starforum.user.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface UserDao {

    @Select("SELECT `id`,`mobile`,`password`,`nickname`,`sex`,`birthday`,`email`,`regdate`,`updatedate`,`lastdate`,`fanscount`,`followcount` " +
            "FROM tb_user")
    @Results(id = "user",
            value = {
                    @Result(id = true, column ="id" ,property = "id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "mobile", property = "mobile", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "password", property = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "nickname", property = "nickName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "sex", property = "sex", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "birthday", property = "birthday", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "regdate", property = "regDate", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "updatedate", property = "updateDate", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "lastdate", property = "lastDate", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "fanscount", property = "fansCount", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "followcount", property = "followCount", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
            }
    )
    List<User> findAll();

    @Select("SELECT `id`,`mobile`,`password`,`nickname`,`sex`,`birthday`,`email`,`regdate`,`updatedate`,`lastdate`,`fanscount`,`followcount` " +
            "FROM tb_user " +
            "WHERE mobile = #{mobile}")
    @ResultMap("user")
    User findUserByMobile(User user);

}
