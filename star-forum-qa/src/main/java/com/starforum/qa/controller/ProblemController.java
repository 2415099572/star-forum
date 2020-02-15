package com.starforum.qa.controller;

import com.starforum.entity.Result;
import com.starforum.entity.StateCode;
import com.starforum.qa.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/problem")
@CrossOrigin
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAllProblem(){
        return new Result(true, StateCode.SUCCESS, "查询成功", problemService.findAllProblem());
    }

    @RequestMapping(value = "/{problemId}", method = RequestMethod.GET)
    public Result findProblemById(@PathVariable("problemId") String problemId){
        return new Result(true, StateCode.SUCCESS, "查询成功", problemService.findProblemById(problemId));
    }
}
