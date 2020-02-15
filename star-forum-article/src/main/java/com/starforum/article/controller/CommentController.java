package com.starforum.article.controller;

import com.starforum.article.service.CommentService;
import com.starforum.entity.Result;
import com.starforum.entity.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public Result findCommentByArticleId(@PathVariable("articleId") String articleId){
        return new Result(true, StateCode.SUCCESS, "查询成功", commentService.findCommentByArticleId(articleId));
    }
}
