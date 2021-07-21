package com.careSystem.service;

import com.careSystem.pojo.FQ_Link;
import com.careSystem.pojo.Question;

import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/13.
 */
public interface FQLinkService {
    //找到所有关联的模板问题
    List<FQ_Link> findAll();

    //新增
    Boolean add(List<FQ_Link> fqLinks);

    //删除
    Boolean delete(List<FQ_Link> fqLinks);

    //找到模板对应的问题
    List<Question> findQuestion(int formId);

    //找到问题对应的关系
    void deleteLinkByQuestionId(List<Integer> questionIds);

    //找到模板对应的关系
    void deleteLinkByFormId(List<Integer> FormIds);
}
