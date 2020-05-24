package com.starforum.soft.service;

import com.starforum.soft.dao.SoftDao;
import com.starforum.soft.entity.Soft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SoftService {
    @Autowired
    private SoftDao softDao;

    public List<Soft> findAll(){
        return softDao.findAll();
    }

    public void addSoft(Soft soft){
        String id = "2";
        soft.setId(id);
        soft.setCreateTime(new Date());
        soft.setUrl("http://localhost:9005/soft/details?id="+id);
        softDao.addSoft(soft);
    }

    public Soft findSoftById(String softId){
        return softDao.findSoftById(softId);
    }
}
