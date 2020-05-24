package com.starforum.article.dao;

import com.starforum.article.entity.Article;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface ArticleDao {

    @Results(id = "article1",
            value = {
                    @Result(id = true, column ="id" ,property = "id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "userid", property = "userId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "title", property = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "content", property = "content", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "createtime", property = "createTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "updatetime", property = "updateTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "isrecommend", property = "isRecommend", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "visits", property = "visits", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "thumbup", property = "thumbup", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "comment", property = "comment", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "state", property = "state", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "url", property = "url", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "labelid", property = "labelId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            }
    )
    @Select("SELECT `id`, `userid`, `title`, `content`, `createtime`, `updatetime`, `isrecommend`, `visits`, `thumbup`, `comment`, `state`, `url`, `labelid` FROM tb_article")
    public List<Article> findAll();

    @Results(id = "article2",
            value = {
                    @Result(id = true, column ="id" ,property = "id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "userid", property = "userId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "title", property = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "content", property = "content", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "createtime", property = "createTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "updatetime", property = "updateTime", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "isrecommend", property = "isRecommend", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "visits", property = "visits", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "thumbup", property = "thumbup", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "comment", property = "comment", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "state", property = "state", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
                    @Result(column = "url", property = "url", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "labelid", property = "labelId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "id", property = "comments", many = @Many(select = "com.starforum.article.dao.CommentDao.findCommentByArticleId", fetchType = FetchType.LAZY))
            }
    )
    @Select("SELECT `id`, `userid`, `title`, `content`, `createtime`, `updatetime`, `isrecommend`, `visits`, `thumbup`, `comment`, `state`, `url`, `labelid` FROM tb_article WHERE id = #{articleId}")
    public Article findArticleById(@Param("articleId") String articleId);

    @ResultMap(value = "article1")
    @Select("SELECT `id`, `userid`, `title`, `content`, `createtime`, `updatetime`, `isrecommend`, `visits`, `thumbup`, `comment`, `state`, `url`, `labelid` FROM tb_article WHERE isrecommend = 1 ORDER BY thumbup DESC")
    public List<Article> findHotArticle();

    @Insert("INSERT INTO tb_article(id, userid, title, content, createtime, url, labelid) VALUES(#{id}, #{userId}, #{title}, #{content}, #{createTime}, #{url}, #{labelId})")
    public void addArticle(Article article);

    @ResultMap(value = "article1")
    @Select("SELECT `id`, `userid`, `title`, `content`, `createtime`, `updatetime`, `isrecommend`, `visits`, `thumbup`, `comment`, `state`, `url`, `labelid` FROM tb_article WHERE userid = #{userId}")
    List<Article> findArticleByUserId(@Param("userId") String userId);
}
