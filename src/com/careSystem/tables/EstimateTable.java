package com.careSystem.tables;

import com.careSystem.pojo.Estimate;
import com.careSystem.service.EstimateService;
import com.careSystem.utils.Util;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/15.
 */
public class EstimateTable extends AbstractTableModel {
    //所有列名 String[]
    private String[] colNames = {"姓名", "性别", "模板名称", "模板类型", "时间", "评估人", "建议"};
    //数据集Object[][]
    private Object[][] data;

    //Service对象
    private EstimateService estimateService = (EstimateService) Util.getObject("estimate.service");

    public EstimateTable() {
        List<Estimate> list = estimateService.getAll();
        System.out.println(list);//测试
        if (list != null && !list.isEmpty()) {
            data = new Object[list.size()][9];
            int i = 0;
            for (Estimate estimate : list) {
                data[i][0] = estimate.getName();
                data[i][1] = estimate.getSex();
                data[i][2] = estimate.getFormWorkName();
                data[i][3] = estimate.getFormWorkType();
                data[i][4] = estimate.getDate();
                data[i][5] = estimate.getEstimator();
                data[i][6] = estimate.getAdvice();
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
        return false;
    }

}
