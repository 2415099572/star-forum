package com.starforum.article.controller;

import com.starforum.article.service.ArticleService;
import com.starforum.entity.Result;
import com.starforum.entity.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StateCode.SUCCESS, "查询成功", articleService.findAll());
    }

    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public Result findArticleById(@PathVariable String articleId) {
        return new Result(true, StateCode.SUCCESS, "查询成功", articleService.findArticleById(articleId));
    }
}
