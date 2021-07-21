package com.careSystem.service.impl;

import com.careSystem.dao.FormDao;
import com.careSystem.pojo.FormWork;
import com.careSystem.service.FormService;
import com.careSystem.utils.Util;

import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/12.
 */
public class FormServiceImpl implements FormService {
    public static FormServiceImpl formService = new FormServiceImpl();
    private FormDao formDao = (FormDao) Util.getObject("form.dao");

    private FormServiceImpl() {
    }

    public static FormServiceImpl getInstance() {
        return formService;
    }

    public static void main(String[] args) {
//        getInstance().add(new FormWork(1,"d","b"));
        getInstance().updateForm(new FormWork(1, "押题卷", "B"));
        System.out.println(getInstance().findAll());
    }

    @Override
    public Boolean add(FormWork form) {
        return formDao.add(form);
    }

    @Override
    public List<FormWork> findAll() {
        return formDao.findAll();
    }

    @Override
    public Boolean delete(List<Integer> deleteId) {
        return formDao.delete(deleteId);
    }

    @Override
    public Boolean updateForm(FormWork formWork) {
        return formDao.updateForm(formWork);
    }

    @Override
    public FormWork getFormWork(String formWorkName) {

        List<FormWork> list = findAll();
        for (FormWork ff : list) {
            if (formWorkName.equals(ff.getName())) {
                return ff;
            }
        }
        return null;
    }

    @Override
    public FormWork getFormWork(Integer id) {
        List<FormWork> list = findAll();
        for (FormWork formWork : list) {
            if (id.equals(formWork.getId())) {
                return formWork;
            }
        }
        return new FormWork();
    }

    @Override
    public String[] getList() {
        List<FormWork> list = findAll();
        int len = list.size();
        String[] strings = new String[len];
        for (int i = 0; i < len; i++) {
            strings[i] = list.get(i).getName();
        }
        return strings;
    }

}
