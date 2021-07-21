package com.careSystem.dao.impl;

import com.careSystem.dao.QuesDao;
import com.careSystem.pojo.Question;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/12.
 */
public class QuesDaoImpl implements QuesDao {
    private static QuesDaoImpl quesDaoImpl = new QuesDaoImpl();
    private ObjectMapper om = new ObjectMapper();
    private File file = new File("question.json");

    private QuesDaoImpl() {

    }

    public static QuesDaoImpl getInstance() {
        return quesDaoImpl;
    }

    public static void main(String[] args) {
//        getInstance().add(new Question(1,"d","b"));
//        getInstance().updateQuestion(new Question(1,"押题卷","B"));
        System.out.println(getInstance().findAll());
    }

    @Override
    public Boolean add(Question question) {
        List<Question> list;
        try {
            if (file.exists() && file.length() > 0) {
                list = om.readValue(file, new TypeReference<List<Question>>() {
                });
                int maxNo = list.stream().max(Comparator.comparingInt(Question::getId)).get().getId();
                question.setId(++maxNo);
            } else {
                question.setId(1);
                list = new ArrayList<>();
            }
            list.add(question);
            om.writeValue(file, list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return java.util.List<com.careSystem.pojo.Question>
     * @Description:获取所有问题
     * @date 2021/7/19 10:58
     */
    @Override
    public List<Question> findAll() {

        List<Question> list = null;
        if (file.exists()) {
            try {
                return om.readValue(file, new TypeReference<List<Question>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else list = new ArrayList<>();

        return list;
    }

    @Override
    public Boolean delete(List<Integer> idarray) {
        List<Question> list = getData();
        for (Integer del : idarray) {
            for (Question pp : list) {
                if (pp.getId().equals(del)) {
                    list.remove(pp);//移除
                    break;
                }
            }
        }
        try {
            om.writeValue(file, list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateQuestion(Question question) {
        List<Question> list = getData();
        Question oldques = null;
        for (Question qq : list) {
            if (question.getId().equals(qq.getId())) {
                oldques = qq;
            }
        }
        list.remove(oldques);
        list.add(question);

        try {
            om.writeValue(file, list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return java.util.List<com.careSystem.pojo.Question> 问题列表
     * @Description:读取json文件
     * @date 2021/7/19 10:59
     */
    private List<Question> getData() {

        List<Question> list = null;
        if (file.exists() && file.length() > 0) {
            try {
                list = om.readValue(file, new TypeReference<List<Question>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            list = new ArrayList<>();
        }
        return list;
    }
}
