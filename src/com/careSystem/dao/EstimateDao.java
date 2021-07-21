package com.careSystem.dao;

import com.careSystem.pojo.Estimate;

import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/15.
 */
public interface EstimateDao {
    //获取所有测评
    List<Estimate> getAll();

    //添加一个测评
    Boolean add(Estimate estimate);
}
