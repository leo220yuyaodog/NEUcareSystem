package com.careSystem.dao.impl;

import com.careSystem.dao.FQLinkDao;
import com.careSystem.pojo.FQ_Link;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Enzo Cotter on 2021/7/13.
 */
public class FQLinkDaoImpl implements FQLinkDao {
    private static FQLinkDaoImpl fqLinkDaoImpl = new FQLinkDaoImpl();
    private ObjectMapper om = new ObjectMapper();
    private File file = new File("FQLink.json");

    private FQLinkDaoImpl() {
    }

    public static FQLinkDaoImpl getInstance() {
        return fqLinkDaoImpl;
    }

    @Override
    public List<FQ_Link> findAll() {
        List<FQ_Link> list = null;
        if (file.exists()) {
            try {
                return om.readValue(file, new TypeReference<List<FQ_Link>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else list = new ArrayList<>();

        return list;
    }

    @Override
    public Boolean add(List<FQ_Link> fqLinks) {
        List<FQ_Link> list = new ArrayList<>();
        if (file.exists()) {
            try {
                list = om.readValue(file, new TypeReference<List<FQ_Link>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        list.addAll(fqLinks);
        Set set = new HashSet(list);//转为set,利用其性质，去除重复的对应关系
        List list1 = new ArrayList(set);//再转为list
        try {
            om.writeValue(file, list1);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(List<FQ_Link> fqLinks) {
        List<FQ_Link> list = null;
        if (file.exists()) {
            try {
                list = om.readValue(file, new TypeReference<List<FQ_Link>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        list.removeAll(fqLinks);
        try {
            om.writeValue(file, list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
