package com.careSystem.service.impl;

import com.careSystem.dao.WorkDao;
import com.careSystem.pojo.Worker;
import com.careSystem.service.WorkService;
import com.careSystem.utils.Util;

import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/8.
 */
public class WorkServiceImpl implements WorkService {

    private static WorkServiceImpl workService = new WorkServiceImpl();
    private WorkDao workDao = (WorkDao) Util.getObject("work.dao");

    private WorkServiceImpl() {
    }

    public static WorkService getInstance() {
        return workService;
    }

//    public static void main(String[] args) {
//
//        System.out.println(workService.findAll());
//    }

    @Override
    public Boolean add(Worker worker) {
        return workDao.add(worker);
    }

    @Override
    public List<Worker> findAll() {
        return workDao.findAll();
    }

    @Override
    public Boolean delete(String[] deleteWorks) {
        return workDao.delete(deleteWorks);
    }

    @Override
    public int login(@org.jetbrains.annotations.NotNull String username, String password) {
        if (username.equals("admin")) {
            if (password.equals("admin")) {
                return 1;
            } else {
                return -1;
            }
        } else {
            Worker worker = findByUsername(username);
            if (worker == null) {
                return -2;
            } else if (worker.getPassword().equals(password)) {
                return 2;
            } else return -1;
        }
    }

    @Override
    public Worker findByUsername(String username) {
        return workDao.findByUsername(username);
    }

    @Override
    public Boolean updateWorker(List<Worker> list) {
        return workDao.updateWorker(list);
    }

    @Override
    public Boolean updateMy(Worker worker) {
        return workDao.updateMy(worker);
    }
}
