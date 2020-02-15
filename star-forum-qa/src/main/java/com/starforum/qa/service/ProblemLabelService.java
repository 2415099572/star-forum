package com.starforum.qa.service;

import com.starforum.qa.dao.ProblemLabelDao;
import com.starforum.qa.entity.ProblemLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemLabelService {

    @Autowired
    ProblemLabelDao problemLabelDao;

    public List<ProblemLabel> findAllPL(){
        return problemLabelDao.findAllPL();
    }

    public List<String> findLabelIdByProblemId(String problemId){
        return problemLabelDao.findLabelIdByProblemId(problemId);
    }
}
