package com.careSystem.tables;

import com.careSystem.pojo.FQ_Link;
import com.careSystem.pojo.Question;
import com.careSystem.service.QuesService;
import com.careSystem.service.impl.FQLinkServiceImpl;
import com.careSystem.utils.Util;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/13.
 */
public class FQLinkTable extends AbstractTableModel {

    //所有列名 String[]
    private String[] colNames = {"", "ID", "题目", "类型"};
    //数据集Object[][]
    private Object[][] data;
    //Service对象
    private QuesService QuesService = (QuesService) Util.getObject("question.service");

    public FQLinkTable(Integer id) {
        List<Question> list = QuesService.findAll();
        List<FQ_Link> fqLinks = FQLinkServiceImpl.getInstance().findAll();
        List<Integer> match = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
//        System.out.println(list);测试
        if (fqLinks != null && !fqLinks.isEmpty()) {
            for (FQ_Link link : fqLinks) {
                if (id == link.getFid()) {
                    match.add(link.getQid());
                }
            }
        }
        if (match.size() != 0) {
            for (Integer matchid : match) {
                for (Question q : list) {
                    if (matchid == q.getId()) {
                        questionList.add(q);
                    }
                }
            }
            if (questionList.size() == 0) {
                this.data = new Object[0][0];
            } else {
                data = new Object[questionList.size()][4];
                int i = 0;
                for (Question ff : questionList) {
                    data[i][0] = Boolean.FALSE;    //复选框
                    data[i][1] = ff.getId();
                    data[i][2] = ff.getQuestion();
                    data[i][3] = ff.getType();
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
