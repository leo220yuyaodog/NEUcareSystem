package com.careSystem.dao;

import com.careSystem.pojo.Question;

import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/12.
 */
public interface QuesDao {
    //添加问题
    Boolean add(Question question);

    //获得所有问题
    List<Question> findAll();

    //删除问题
    Boolean delete(List<Integer> array);

    //修改问题信息
    Boolean updateQuestion(Question question);
}
