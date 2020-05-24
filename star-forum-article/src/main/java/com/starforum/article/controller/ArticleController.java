package com.starforum.article.controller;

import com.starforum.article.entity.Article;
import com.starforum.article.service.ArticleService;
import com.starforum.entity.Result;
import com.starforum.entity.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @RequestMapping(value = "/hot", method = RequestMethod.GET)
    public Result findHotArticle(){
        return new Result(true, StateCode.SUCCESS, "查询成功", articleService.findHotArticle());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addArticle(@RequestBody Article article){
        try {
            articleService.addArticle(article);
            return new Result(true, StateCode.SUCCESS, "添加成功");
        }catch (Exception e){
            return new Result(false, StateCode.ERROR, "添加失败");
        }

    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public Result findArticleByUserId(@PathVariable("userId") String userId){
        return new Result(true, StateCode.SUCCESS, "查询成功", articleService.findArticleByUserId(userId));
    }
}
