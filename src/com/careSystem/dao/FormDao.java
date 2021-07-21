package com.careSystem.dao;

import com.careSystem.pojo.FormWork;

import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/12.
 */
public interface FormDao {
    //添加模板
    Boolean add(FormWork formWork);

    //获得所有模板
    List<FormWork> findAll();

    //删除模板
    Boolean delete(List<Integer> deleteId);

    //修改模板信息
    Boolean updateForm(FormWork formWork);
}
