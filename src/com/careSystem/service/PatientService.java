package com.careSystem.service;

import com.careSystem.pojo.Patient;

import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/8.
 */
public interface PatientService {
    //添加病人
    Boolean add(Patient patient);

    //获得所有病人
    List<Patient> findAll();

    //删除病人
    Boolean delete(String[] idarray);

    //修改病人信息
    Boolean updatePatient(List<Patient> list);

    //通过id查找病人
    Patient findById(String id);
}
