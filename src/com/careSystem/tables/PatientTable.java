package com.careSystem.tables;

import com.careSystem.pojo.Patient;
import com.careSystem.service.PatientService;
import com.careSystem.utils.Util;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/9.
 *
 * @description 病人表格
 * 1.继承 AbstractTableModel
 * 2.重写方法
 * int getRowCount()
 * int getColumnCount()
 * Object getValueAt(int rowIndex, int columnIndex)
 * String getColumnName(int column)
 * Class<?> getColumnClass(int columnIndex)
 * boolean isCellEditable(int rowIndex, int columnIndex)
 */
public class PatientTable extends AbstractTableModel {

    //所有列名 String[]
    private String[] colNames = {"", "姓名", "年龄", "性别", "身份证", "联系电话", "紧急联系电话", "紧急联系人", ""};
    //数据集Object[][]
    private Object[][] data;

    //Service对象
    private PatientService patientService = (PatientService) Util.getObject("patient.service");

    public PatientTable() {
        int cnt = 0;
        List<Patient> list = patientService.findAll();
//        System.out.println(list);//测试
        if (list != null && !list.isEmpty()) {
            for (Patient patient : list) {
                if (patient.getDeleted() == 0) {
                    cnt++;//统计病人数量
                }
            }
            data = new Object[cnt][9];
            int i = 0;
            for (Patient pp : list) {
                if (pp.getDeleted() == 0) {
                    data[i][0] = Boolean.FALSE;    //复选框
                    data[i][1] = pp.getName();
                    data[i][2] = pp.getAge();
                    data[i][3] = pp.getSex();
                    data[i][4] = pp.getId_number();
                    data[i][5] = pp.getPhone();
                    data[i][6] = pp.getEmergency_number();
                    data[i][7] = pp.getEmergency_contact();
                    data[i][8] = "";
                    i++;
                }
            }

        } else {
            this.data = new Object[0][0];

        }
    }

    public PatientTable(String goal) {
        List<Patient> list = patientService.findAll();
        List<Patient> patientList = new ArrayList<>();
//        System.out.println(list);//测试
        for (Patient patient : list) {
            if (patient.getDeleted() == 0) {
                if (patient.getName().contains(goal)) {
                    patientList.add(patient);
                }
            }
        }
        System.out.println(patientList);
        if (patientList.size() != 0) {
            data = new Object[patientList.size()][9];
            int i = 0;
            for (Patient pp : patientList) {
                if (pp.getName().contains(goal)) {
                    data[i][0] = Boolean.FALSE;    //复选框
                    data[i][1] = pp.getName();
                    data[i][2] = pp.getAge();
                    data[i][3] = pp.getSex();
                    data[i][4] = pp.getId_number();
                    data[i][5] = pp.getPhone();
                    data[i][6] = pp.getEmergency_number();
                    data[i][7] = pp.getEmergency_contact();
                    data[i][8] = "";
                    i++;
                }
            }
        } else {
            this.data = new Object[0][0];

        }
    }

    //返回结果行数
    @Override
    public int getRowCount() {
        return this.data.length;
    }

    //返回结果列数
    @Override
    public int getColumnCount() {
        return this.colNames.length;
    }

    //返回某行某列对应的数据
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    //获得对应列名
    public String getColumnName(int column) {
        return this.colNames[column];
    }

    @Override
    //每列对应的数据类型
    public Class<?> getColumnClass(int columnIndex) {
        return data[0][columnIndex].getClass();
    }

    //修改表格中的数据
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
        /*修改表格数据后，显示改后结果*/
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    /**
     * 哪列不能编辑
     * 返回false 不可编辑
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1) {        //编号不能编辑
            return false;
        } else return true;
    }
}
