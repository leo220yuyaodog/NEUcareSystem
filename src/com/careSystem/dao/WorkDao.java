package com.careSystem.dao;

import com.careSystem.pojo.Worker;

import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/8.
 */
public interface WorkDao {
    //添加职工
    Boolean add(Worker worker);

    //获得所有职工
    List<Worker> findAll();

    //删除职工
    Boolean delete(String[] deleteWorks);

    //通过用户名查找
    Worker findByUsername(String username);

    //修改职工信息
    Boolean updateWorker(List<Worker> list);

    //修改个人信息
    Boolean updateMy(Worker work);

}
