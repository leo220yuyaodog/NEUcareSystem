package com.careSystem.service.impl;

import com.careSystem.dao.QuesDao;
import com.careSystem.pojo.Question;
import com.careSystem.service.QuesService;
import com.careSystem.utils.Util;

import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/12.
 */
public class QuesServiceImpl implements QuesService {
    public static QuesServiceImpl quesServiceImpl = new QuesServiceImpl();
    private QuesDao quesDao = (QuesDao) Util.getObject("question.dao");

    private QuesServiceImpl() {
    }

    public static QuesServiceImpl getInstance() {
        return quesServiceImpl;
    }

//    public static void main(String[] args) {
////        getInstance().add(new Question(1,"押题卷","啊","啊","额","v"));
//        getInstance().updateQuestion(new Question(1,"押题卷","B","a","a","A"));
//        System.out.println(getInstance().findAll());
//    }

    @Override
    public Boolean add(Question question) {
        return quesDao.add(question);
    }

    @Override
    public List<Question> findAll() {
        return quesDao.findAll();
    }

    @Override
    public Boolean delete(List<Integer> idarray) {
        return quesDao.delete(idarray);
    }

    @Override
    public Boolean updateQuestion(Question question) {
        return quesDao.updateQuestion(question);
    }

    @Override
    public Question findByID(int id) {
        for (Question question : quesDao.findAll()) {
            if (id == question.getId()) {
                return question;
            }
        }
        return null;
    }
}
