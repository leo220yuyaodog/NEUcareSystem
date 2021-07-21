package com.careSystem.tables;

import com.careSystem.pojo.Worker;
import com.careSystem.service.WorkService;
import com.careSystem.utils.Util;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/9.
 *
 * @description 职工表格
 * 1.继承 AbstractTableModel
 * 2.重写方法
 * int getRowCount()
 * int getColumnCount()
 * Object getValueAt(int rowIndex, int columnIndex)
 * String getColumnName(int column)
 * Class<?> getColumnClass(int columnIndex)
 * boolean isCellEditable(int rowIndex, int columnIndex)
 */
public class WorkTable extends AbstractTableModel {

    //所有列名 String[]
    private String[] colNames = {"", "用户名", "密码", "姓名", "出生日期", "专长", "职称", "联系电话"};
    //数据集Object[][]
    private Object[][] data;
    //Service对象
    private WorkService workService = (WorkService) Util.getObject("work.service");

    public WorkTable() {
        int cnt = 0;
        List<Worker> list = workService.findAll();
        System.out.println(list);
        if (list != null && !list.isEmpty()) {
            for (Worker worker : list) {
                if (worker.getDeleted() == 0) {
                    cnt++;
                }
            }
            data = new Object[cnt][8];
            int i = 0;
            for (Worker ww : list) {
                if (ww.getDeleted() == 0) {
                    data[i][0] = Boolean.FALSE;    //复选框
                    data[i][1] = ww.getUsername();
                    data[i][2] = ww.getPassword();
                    data[i][3] = ww.getName();
                    data[i][4] = ww.getBirthday();
                    data[i][5] = ww.getSpeciality();
                    data[i][6] = ww.getProfession();
                    data[i][7] = ww.getPhone();
                    i++;
                }
            }
        } else {
            this.data = new Object[0][0];
        }
    }

    public WorkTable(String goal) {

        List<Worker> list = workService.findAll();
        List<Worker> workers = new ArrayList<>();
        for (Worker worker : list) {
            if (worker.getDeleted() == 0) {
                if (worker.getName().contains(goal)) {
                    workers.add(worker);
                }
            }
        }
        System.out.println(workers);
        if (!(workers.size() == 0)) {

            data = new Object[workers.size()][8];
            int i = 0;
            for (Worker ww : workers) {

                data[i][0] = Boolean.FALSE;    //复选框
                data[i][1] = ww.getUsername();
                data[i][2] = ww.getPassword();
                data[i][3] = ww.getName();
                data[i][4] = ww.getBirthday();
                data[i][5] = ww.getSpeciality();
                data[i][6] = ww.getProfession();
                data[i][7] = ww.getPhone();
                i++;
            }
        } else {
            this.data = new Object[0][0];
        }
    }

    public WorkTable(String goal, int type) {

        List<Worker> list = workService.findAll();
        List<Worker> workerList = new ArrayList<>();
        for (Worker worker : list) {
            if (worker.getDeleted() == 0) {
                if (worker.getProfession().equals(goal)) {
                    workerList.add(worker);
                }
            }
        }
        System.out.println(workerList);
        if (!(workerList.size() == 0)) {
            data = new Object[workerList.size()][8];
            int i = 0;
            for (Worker ww : workerList) {

                data[i][0] = Boolean.FALSE;    //复选框
                data[i][1] = ww.getUsername();
                data[i][2] = ww.getPassword();
                data[i][3] = ww.getName();
                data[i][4] = ww.getBirthday();
                data[i][5] = ww.getSpeciality();
                data[i][6] = ww.getProfession();
                data[i][7] = ww.getPhone();
                i++;
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

    //返回一个位置对应的数据
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
        if (columnIndex == 1 || columnIndex == 6) {        //编号不能编辑
            return false;
        } else return true;
    }
}
