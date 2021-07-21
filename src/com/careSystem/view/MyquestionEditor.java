package com.careSystem.view;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

/**
 * Created by Enzo Cotter on 2021/7/15.
 */
public class MyquestionEditor extends AbstractButton implements TableCellRenderer, TableCellEditor, ActionListener {
    private JButton button;
    private JTable table;
    private int row;
    private int colmun;

    public MyquestionEditor(JTable table) {
        this.button = new JButton("详情");
        this.table = table;
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println(table.getValueAt(row, 1));//测试，打印id
        int id = (int) table.getValueAt(row, 1);//获取id
        AddQuestionFrame.run(id);//打开添加问题界面

    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public boolean isCellEditable(EventObject eventObject) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject eventObject) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        return true;
    }

    @Override
    public void cancelCellEditing() {

    }

    @Override
    public void addCellEditorListener(CellEditorListener cellEditorListener) {

    }

    @Override
    public void removeCellEditorListener(CellEditorListener cellEditorListener) {

    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1) {
        return button;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        this.colmun = column;
        return button;
    }
}
