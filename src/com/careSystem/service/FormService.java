package com.careSystem.service;

import com.careSystem.pojo.FormWork;

import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/12.
 */
public interface FormService {

    //添加模板
    Boolean add(FormWork form);

    //获得所有模板
    List<FormWork> findAll();

    //删除模板
    Boolean delete(List<Integer> deleteId);

    //修改模板信息
    Boolean updateForm(FormWork formWork);

    //通过模板名查找模板
    FormWork getFormWork(String formWorkName);

    //通过模板id查找模板
    FormWork getFormWork(Integer id);

    //找到所有模板名
    String[] getList();

}
