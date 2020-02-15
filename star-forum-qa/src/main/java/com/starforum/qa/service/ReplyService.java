package com.starforum.qa.service;

import com.starforum.qa.dao.ReplyDao;
import com.starforum.qa.entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReplyService {
    @Autowired
    ReplyDao replyDao;

    public List<Reply> findAllReply(){
        return replyDao.findAllReply();
    }
}
