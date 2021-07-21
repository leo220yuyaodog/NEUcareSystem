package com.careSystem.tables;

import com.careSystem.pojo.FormWork;
import com.careSystem.service.FormService;
import com.careSystem.utils.Util;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/12.
 */
public class FormTable extends AbstractTableModel {
    //所有列名 String[]
    private String[] colNames = {"", "ID", "模板名", "类型"};
    //数据集Object[][]
    private Object[][] data;
    //Service对象
    private FormService formService = (FormService) Util.getObject("form.service");

    public FormTable() {
        int cnt = 0;
        List<FormWork> list = formService.findAll();
        if (list != null && !list.isEmpty()) {

            data = new Object[list.size()][4];
            int i = 0;
            for (FormWork ff : list) {
                data[i][0] = Boolean.FALSE;    //复选框
                data[i][1] = ff.getId();
                data[i][2] = ff.getName();
                data[i][3] = ff.getType();
                i++;
            }
        } else {
            this.data = new Object[0][0];
        }

    }

    public FormTable(String type) {
        int cnt = 0;
        List<FormWork> list = formService.findAll();
        List<FormWork> formWorkList = new ArrayList<>();
        for (FormWork formWork : list) {
            if (type.equals(formWork.getType())) {
                formWorkList.add(formWork);
            }
        }
        System.out.println(formWorkList);
        if (formWorkList != null && !formWorkList.isEmpty()) {

            data = new Object[formWorkList.size()][4];
            int i = 0;
            for (FormWork ff : formWorkList) {
                data[i][0] = Boolean.FALSE;    //复选框
                data[i][1] = ff.getId();
                data[i][2] = ff.getName();
                data[i][3] = ff.getType();
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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return true;
        else return false;
    }
}
