package com.starforum.base.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.starforum.base.dao.LabelDao;
import com.starforum.base.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {

    @Autowired
    LabelDao labelDao;

    public List<Label> findAll(){
       return labelDao.findAll();
    }

    public Label findById(long labelId) {
        return labelDao.findById(labelId);
    }

    public void save(Label label) {
        labelDao.save(label);
    }

    public void delete(long labelId) {
        labelDao.delete(labelId);
    }

    public void update(Label label) {
        labelDao.update(label);
    }

    public List<Label> query(Label label){
        return labelDao.query(label);
    }

    public PageInfo<Label> pageQuery(Label label, int page, int size) {
        PageInfo<Label> pageInfo = PageHelper.startPage(page, size).doSelectPageInfo(()->labelDao.query(label));
        return pageInfo;
    }
}
