package com.starforum.qa.controller;

import com.starforum.entity.Result;
import com.starforum.entity.StateCode;
import com.starforum.qa.service.ProblemLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/problemLabel")
@CrossOrigin
public class ProblemLabelController {

    @Autowired
    ProblemLabelService problemLabelService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAllPL(){
        return new Result(true, StateCode.SUCCESS, "查询成功", problemLabelService.findAllPL());
    }

    @RequestMapping(value = "/{problemId}", method = RequestMethod.GET)
    public Result findAllPL(@PathVariable("problemId") String problemId){
        return new Result(true, StateCode.SUCCESS, "查询成功", problemLabelService.findLabelIdByProblemId(problemId));
    }
}
