package com.careSystem.dao.impl;

import com.careSystem.dao.FormDao;
import com.careSystem.pojo.FormWork;
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
public class FormDaoImpl implements FormDao {
    private static FormDaoImpl formDaoImpl = new FormDaoImpl();
    private ObjectMapper om = new ObjectMapper();
    private File file = new File("formwork.json");

    private FormDaoImpl() {

    }

    public static FormDaoImpl getInstance() {
        return formDaoImpl;
    }

    public static void main(String[] args) {
        getInstance().add(new FormWork(1, "d", "b"));
        getInstance().updateForm(new FormWork(1, "押题卷", "B"));
        System.out.println(getInstance().findAll());
    }

    /**
     * @param formWork 模板实体类
     * @return java.lang.Boolean
     * @Description:添加一个模板
     * @date 2021/7/19 10:32
     */
    @Override
    public Boolean add(FormWork formWork) {

        List<FormWork> list;
        try {
            if (file.exists() && file.length() > 0) {
                list = om.readValue(file, new TypeReference<List<FormWork>>() {
                });
                int maxNo = list.stream().max(Comparator.comparingInt(FormWork::getId)).get().getId();
                formWork.setId(++maxNo);
            } else {
                formWork.setId(1);
                list = new ArrayList<>();
            }
            for (FormWork ff : list) {
                if (formWork.getName().equals(ff.getName()))
                    return false;//模板名字重复则添加失败
            }

            list.add(formWork);
            om.writeValue(file, list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return java.util.List<com.careSystem.pojo.FormWork>
     * @Description:获取所有模板
     * @date 2021/7/19 10:33
     */
    @Override
    public List<FormWork> findAll() {

        List<FormWork> list = null;
        if (file.exists()) {
            try {
                return om.readValue(file, new TypeReference<List<FormWork>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else list = new ArrayList<>();

        return list;
    }

    /**
     * @param deleteId 要被删除的模板id
     * @return java.lang.Boolean
     * @Description:删除模板
     * @date 2021/7/19 10:38
     */
    @Override
    public Boolean delete(List<Integer> deleteId) {

        List<FormWork> list = getData();
        for (Integer del : deleteId) {
            for (FormWork pp : list) {
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

    /**
     * @param formWork 要修改的对象
     * @return java.lang.Boolean
     * @Description:修改模板信息
     * @date 2021/7/19 10:42
     */
    @Override
    public Boolean updateForm(FormWork formWork) {

        List<FormWork> list = getData();
        FormWork oldform = null;
        for (FormWork ff : list) {
            if (formWork.getId().equals(ff.getId())) {//通过找到模板
                oldform = ff;//存储旧模版信息
            }
        }
        list.remove(oldform);//删除旧模板
        list.add(formWork);//加入新模板

        try {
            om.writeValue(file, list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return java.util.List<com.careSystem.pojo.FormWork>
     * @Description:读取json文件
     * @date 2021/7/19 10:44
     */
    private List<FormWork> getData() {

        List<FormWork> list = null;
        if (file.exists() && file.length() > 0) {
            try {
                list = om.readValue(file, new TypeReference<List<FormWork>>() {
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
