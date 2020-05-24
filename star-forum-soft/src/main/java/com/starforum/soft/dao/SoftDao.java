package com.starforum.soft.dao;

import com.starforum.soft.entity.Soft;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface SoftDao {
    @Results(id = "soft",
            value =  {
                    @Result(id = true, column ="id" ,property = "id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "name", property = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "describ", property = "describe", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "createtime", property = "createTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "userid", property = "userId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "nickname", property = "nickname", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "download", property = "download", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "thumbup", property = "thumbup", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "labelid", property = "labelId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "path", property = "path", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "url", property = "url", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            })
    @Select("SELECT id, userid, nickname, name, labelid, createtime, path, url, thumbup, download, describ FROM tb_soft")
    public List<Soft> findAll();

    @Insert("INSERT INTO tb_soft(`id`, `userid`, `nickname`, `name`, `createtime`, `labelid`, `describ`, `path`, `url`) " +
            "VALUES(#{id}, #{userId}, #{nickname}, #{name}, #{createTime}, #{labelId}, #{describe}, #{path}, #{url})")
    public void addSoft(Soft soft);

    @ResultMap(value = "soft")
    @Select("SELECT id, userid, nickname, name, labelid, createtime, path, url, thumbup, download, describ FROM tb_soft WHERE id = #{softId}")
    public Soft findSoftById(@Param("softId") String softId);
}
