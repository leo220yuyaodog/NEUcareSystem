package com.careSystem.dao.impl;

import com.careSystem.dao.PatientDao;
import com.careSystem.pojo.Patient;
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
public class PatientDaoImpl implements PatientDao {

    private static PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
    private ObjectMapper om = new ObjectMapper();
    private File file = new File("patient.json");

    private PatientDaoImpl() {

    }

    public static PatientDaoImpl getInstance() {
        return patientDaoImpl;
    }

    public static void main(String[] args) {
        getInstance().add(new Patient(1, "a", "a", "a", "a", "a", "a", "a"));
        System.out.println(getInstance().findAll());
    }

    /**
     * @param patient 病人对象
     * @return java.lang.Boolean
     * @Description:添加一个病人
     * @date 2021/7/19 10:48
     */
    public Boolean add(Patient patient) {
        List<Patient> list;
        try {
            if (file.exists() && file.length() > 0) {
                list = om.readValue(file, new TypeReference<List<Patient>>() {
                });
                int maxNo = list.stream().max(Comparator.comparingInt(Patient::getId)).get().getId();
                patient.setId(++maxNo);//设置id为最大id+1
            } else {
                patient.setId(1);
                list = new ArrayList<>();
            }
            for (Patient pp : list) {
                if (patient.getId_number().equals(pp.getId_number())) {
                    return false;//身份证号相同，则添加失败
                }
            }
            list.add(patient);
            om.writeValue(file, list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return java.util.List<com.careSystem.pojo.Patient>
     * @Description:获取所有病人
     * @date 2021/7/19 10:54
     */
    @Override
    public List<Patient> findAll() {

        List<Patient> list = null;
        if (file.exists()) {
            try {
                return om.readValue(file, new TypeReference<List<Patient>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else list = new ArrayList<>();

        return list;
    }

    /**
     * @param idarray 要删除的病人的id
     * @return java.lang.Boolean
     * @Description:删除病人
     * @date 2021/7/19 10:57
     */
    @Override
    public Boolean delete(String[] idarray) {

        List<Patient> list = getData();
        for (String del : idarray) {
            for (Patient pp : list) {
                if (pp.getName().equals(del)) {
                    Patient dwork = pp;
                    dwork.setDeleted(1);//逻辑删除，避免物理删除
                    list.remove(pp);//移除
                    list.add(dwork);//添加 isdeleted=1
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

    private List<Patient> getData() {
        List<Patient> list = null;
        if (file.exists() && file.length() > 0) {
            try {
                list = om.readValue(file, new TypeReference<List<Patient>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public Boolean updatePatient(List<Patient> list) {
        try {
            om.writeValue(file, list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param id 病人的身份证号
     * @return com.careSystem.pojo.Patient 病人对象
     * @Description:通过身份证号码查找病人
     * @date 2021/7/19 10:55
     */
    @Override
    public Patient findById(String id) {

        for (Patient pp : getData()) {
            if (id.equals(pp.getId_number())) {
                return pp;
            }
        }
        return null;
    }
}
