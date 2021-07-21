package com.careSystem.dao.impl;

import com.careSystem.dao.EstimateDao;
import com.careSystem.pojo.Estimate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/15.
 */
public class EstimateDaoImpl implements EstimateDao {

    private static EstimateDaoImpl estimateDao = new EstimateDaoImpl();
    private ObjectMapper om = new ObjectMapper();
    private File file = new File("estimate.json");

    private EstimateDaoImpl() {

    }

    /**
     * @return com.careSystem.dao.impl.EstimateDaoImpl
     * @Description:得到一个EstimateDaoImpl单例对象
     * @date 2021/7/19 10:30
     */
    public static EstimateDaoImpl getInstance() {

        return estimateDao;
    }

    /**
     * @return java.util.List<com.careSystem.pojo.Estimate>
     * @Description:获取所有评估结果
     * @date 2021/7/19 10:29
     */
    @Override
    public List<Estimate> getAll() {

        List<Estimate> list = null;
        if (file.exists()) {
            try {
                return om.readValue(file, new TypeReference<List<Estimate>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else list = new ArrayList<>();
        return list;
    }

    /**
     * @param estimate 评估实体类
     * @return java.lang.Boolean
     * @Description:添加一个评估结果
     * @date 2021/7/19 10:29
     */
    @Override
    public Boolean add(Estimate estimate) {

        List<Estimate> list;
        try {
            if (file.exists() && file.length() > 0) {
                list = om.readValue(file, new TypeReference<List<Estimate>>() {
                });
                int maxNo = list.stream().max(Comparator.comparingInt(Estimate::getId)).get().getId();//算出所有id中最大id
                estimate.setId(++maxNo);//新建的模板id+1
            } else {
                estimate.setId(1);
                list = new ArrayList<>();
            }

            list.add(estimate);
            om.writeValue(file, list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

