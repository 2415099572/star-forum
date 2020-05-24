package com.starforum.qa.service;

import com.starforum.qa.dao.ProblemDao;
import com.starforum.qa.entity.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {

    @Autowired
    ProblemDao problemDao;

    public List<Problem> findAllProblem(){
        return problemDao.findAllProblem();
    }

    public Problem findProblemById(String problemId){
        return problemDao.findProblemById(problemId);
    }

    public List<Problem> findHotProblem(){
        return problemDao.findHotProblem();
    }
}
