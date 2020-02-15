package com.starforum.base.sql;

import com.starforum.base.entity.Label;

import java.util.Map;

public class LabelSql {
    public String query(Map<String, Object> map){
        Label label = (Label) map.get("label");
        StringBuilder sql = new StringBuilder("SELECT id, labelname, state, recommend, fans FROM tb_label WHERE 1 = 1");
        if(label.getId() != null){
            sql.append(" and id = '" + label.getId() + "'");
        }
        if(label.getLabelName() != null && !"".equals(label.getLabelName())){
            sql.append(" and labelname like '%" + label.getLabelName() + "%'");
        }
        if(label.getState() != null){
            sql.append(" and state = '" + label.getState() + "'");
        }
        if(label.getRecommend() != null){
            sql.append(" and recommend = '" + label.getRecommend() + "'");
        }
        return sql.toString();
    }
}
