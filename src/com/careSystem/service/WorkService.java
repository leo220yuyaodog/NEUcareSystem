package com.careSystem.service;

import com.careSystem.pojo.Worker;

import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/8.
 */
public interface WorkService {
    //添加职工
    Boolean add(Worker worker);

    //获得所有职工
    List<Worker> findAll();

    //删除职工
    Boolean delete(String[] deleteWorks);

    //登录
    int login(String username, String password);

    //通过用户名查找
    Worker findByUsername(String username);

    Boolean updateWorker(List<Worker> list);

    Boolean updateMy(Worker work);


}
