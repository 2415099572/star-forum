package com.starforum.article.service;

import com.starforum.article.dao.ArticleDao;
import com.starforum.article.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    public List<Article> findAll(){
        return articleDao.findAll();
    }

    public Article findArticleById(String articleId){
        return articleDao.findArticleById(articleId);
    }

    public List<Article> findHotArticle(){
        return articleDao.findHotArticle();
    }

    public void addArticle(Article article){

        articleDao.addArticle(article);
    }

    public List<Article> findArticleByUserId(String userId) {
        return articleDao.findArticleByUserId(userId);
    }
}
