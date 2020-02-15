package com.starforum.base.dao;

import com.starforum.base.entity.Label;
import com.starforum.base.sql.LabelSql;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LabelDao {

    @Select("SELECT id, labelname, state, recommend, fans FROM tb_label")
    @Results(id = "label",
            value = {
                    @Result(id = true, column ="id" ,property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
                    @Result(column = "labelname", property = "labelName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "state", property = "state", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "recommend", property = "recommend", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "fans", property = "fans", javaType = Long.class, jdbcType = JdbcType.BIGINT)
            }
    )
    List<Label> findAll();

    @Select("SELECT id, labelname, state, recommend, fans FROM tb_label WHERE id = #{labelId}")
    @ResultMap("label")
    Label findById(@Param("labelId") long labelId);

    @Insert("INSERT INTO tb_label(id, labelname, state, recommend, fans) VALUES(#{id}, #{labelName}, #{state}, #{recommend}, #{fans})")
    void save(Label label);

    @Delete("DELETE FROM tb_label WHERE id = #{labelId}")
    void delete(@Param("labelId") long labelId);


    @Update("UPDATE tb_label SET labelname = #{labelName}, state = #{state}, recommend = #{recommend}, fans = #{fans} WHERE id = #{id}")
    void update(Label label);

    @SelectProvider(type = LabelSql.class, method = "query")
    @ResultMap("label")
    List<Label> query(@Param("label") Label label);
}
