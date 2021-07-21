package com.careSystem.dao.impl;

import com.careSystem.dao.WorkDao;
import com.careSystem.pojo.Worker;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/8.
 */
public class WorkDaoImpl implements WorkDao {

    private static WorkDaoImpl workDaoImpl = new WorkDaoImpl();
    private ObjectMapper om = new ObjectMapper();
    private File file = new File("worker.json");

    private WorkDaoImpl() {

    }

    public static WorkDaoImpl getInstance() {
        return workDaoImpl;
    }

    public static void main(String[] args) {

        getInstance().add(new Worker(1, "a", "a", "a", "a", "a", "a", "a"));

//            getInstance().delete(new Integer[]{1,2,3,4});
        System.out.println(getInstance().findAll());
    }

    /**
     * @param worker 工作人员对象
     * @return java.lang.Boolean 是否添加成功
     * @Description:添加一个工作人员
     * @date 2021/7/19 11:00
     */
    @Override
    public Boolean add(Worker worker) {

        List<Worker> list;
        try {
            if (file.exists() && file.length() > 0) {
                list = om.readValue(file, new TypeReference<List<Worker>>() {
                });
                int maxNo = list.stream().max(Comparator.comparingInt(Worker::getId)).get().getId();
                worker.setId(++maxNo);
            } else {
                worker.setId(1);
                list = new ArrayList<>();
            }
            for (Worker ww : list) {
                if (worker.getUsername().equals(ww.getUsername())) {
                    return false;//如果用户名相同，添加失败
                }
            }
            list.add(worker);
            om.writeValue(file, list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return java.util.List<com.careSystem.pojo.Worker>
     * @Description:获取所有工作人员
     * @date 2021/7/19 11:01
     */
    @Override
    public List<Worker> findAll() {

        List<Worker> list = null;
        if (file.exists() && file.length() > 0) {
            try {
                return om.readValue(file, new TypeReference<List<Worker>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else list = new ArrayList<>();
        return list;
    }

    /**
     * @param deleteWorks 要删除的人的用户名
     * @return java.lang.Boolean
     * @Description:删除工作人员
     * @date 2021/7/19 21:45
     */
    @Override
    public Boolean delete(String[] deleteWorks) {

        List<Worker> list = getData();

        for (String delWork : deleteWorks) {
            for (Worker ww : list) {
                if (ww.getUsername().equals(delWork)) {
                    Worker dwork = ww;
                    dwork.setDeleted(1);//逻辑删除，避免物理删除
                    list.remove(ww);//移除
                    list.add(dwork);//添加 isdeleted=1
                    break;
                }
            }
        }
        try {
            om.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @param username 用户名
     * @return com.careSystem.pojo.Worker
     * @Description:通过用户名查找人员
     * @date 2021/7/19 21:48
     */
    @Override
    public Worker findByUsername(String username) {

        List<Worker> list = getData();

        for (Worker ww : list) {
            if (username.equals(ww.getUsername())) {
                return ww;
            }
        }
        return null;
    }

    /**
     * @param list <List>Worker
     * @return java.lang.Boolean
     * @Description:修改工作人员信息
     * @date 2021/7/19 21:51
     */
    @Override
    public Boolean updateWorker(List<Worker> list) {

        try {
            om.writeValue(file, list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean updateMy(Worker work) {
        List<Worker> list = getData();
        Worker oldworker = null;
        for (Worker ww : list) {
            if (work.getUsername().equals(ww.getUsername())) {
                oldworker = ww;
            }
        }
        list.remove(oldworker);
        list.add(work);

        try {
            om.writeValue(file, list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<Worker> getData() {
        List<Worker> list = null;
        if (file.exists() && file.length() > 0) {
            try {
                list = om.readValue(file, new TypeReference<List<Worker>>() {
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
