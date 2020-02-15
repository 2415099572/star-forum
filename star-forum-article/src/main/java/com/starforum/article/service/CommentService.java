package com.starforum.article.service;

import com.starforum.article.dao.CommentDao;
import com.starforum.article.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    public List<Comment> findCommentByArticleId(String articleId){
        return commentDao.findCommentByArticleId(articleId);
    }
}
