package com.careSystem.dao;

import com.careSystem.pojo.FQ_Link;

import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/13.
 */
public interface FQLinkDao {
    //找到所有关联的模板问题
    List<FQ_Link> findAll();

    //新增
    Boolean add(List<FQ_Link> fqLinks);

    //删除
    Boolean delete(List<FQ_Link> fqLinks);
}
