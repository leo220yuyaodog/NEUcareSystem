package com.careSystem.service.impl;

import com.careSystem.dao.PatientDao;
import com.careSystem.pojo.Patient;
import com.careSystem.service.PatientService;
import com.careSystem.utils.Util;

import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/8.
 */
public class PatientServiceImpl implements PatientService {
    public static PatientServiceImpl patientService = new PatientServiceImpl();
    private PatientDao patientDao = (PatientDao) Util.getObject("patient.dao");

    private PatientServiceImpl() {
    }

    public static PatientServiceImpl getInstance() {
        return patientService;
    }

    public static void main(String[] args) {
        patientService.add(new Patient());
        System.out.println(patientService.findAll());
    }

    @Override
    public Boolean add(Patient patient) {
        return patientDao.add(patient);
    }

    @Override
    public List<Patient> findAll() {
        return patientDao.findAll();
    }

    @Override
    public Boolean delete(String[] idarray) {
        return patientDao.delete(idarray);
    }

    @Override
    public Boolean updatePatient(List<Patient> list) {
        return patientDao.updatePatient(list);
    }

    @Override
    public Patient findById(String id) {
        return patientDao.findById(id);
    }
}
