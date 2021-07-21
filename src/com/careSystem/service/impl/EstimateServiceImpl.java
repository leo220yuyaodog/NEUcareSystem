package com.careSystem.service.impl;

import com.careSystem.dao.EstimateDao;
import com.careSystem.pojo.Estimate;
import com.careSystem.service.EstimateService;
import com.careSystem.utils.Util;

import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/15.
 */
public class EstimateServiceImpl implements EstimateService {

    private static EstimateServiceImpl estimateService = new EstimateServiceImpl();
    EstimateDao estimateDao = (EstimateDao) Util.getObject("estimate.dao");

    private EstimateServiceImpl() {
    }

    public static EstimateServiceImpl getInstance() {
        return estimateService;
    }


    @Override
    public List<Estimate> getAll() {
        return estimateDao.getAll();
    }

    @Override
    public Boolean add(Estimate estimate) {
        return estimateDao.add(estimate);
    }
}
