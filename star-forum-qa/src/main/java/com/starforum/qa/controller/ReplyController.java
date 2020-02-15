package com.starforum.qa.controller;

import com.starforum.entity.Result;
import com.starforum.entity.StateCode;
import com.starforum.qa.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply")
@CrossOrigin
public class ReplyController {
    @Autowired
    ReplyService replyService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAllReply(){
        return new Result(true, StateCode.SUCCESS, "查询成功", replyService.findAllReply());
    }
}
