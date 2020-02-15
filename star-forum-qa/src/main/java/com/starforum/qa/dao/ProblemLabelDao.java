package com.starforum.qa.dao;

import com.starforum.qa.entity.ProblemLabel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProblemLabelDao {
    @Results(id = "problemLabel",
            value = {
                    @Result(id = true, column ="problemid" ,property = "problemId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "labelid", property = "labelId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                   }
    )
    @Select("SELECT `problemid`, `labelid` FROM tb_pl")
    List<ProblemLabel> findAllPL();

    @ResultMap(value = "problemLabel")
    @Select("SELECT `labelid` FROM tb_pl WHERE problemid = #{problemId}")
    List<String> findLabelIdByProblemId(@Param("problemId") String problemId);
}
