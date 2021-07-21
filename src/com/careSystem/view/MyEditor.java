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
 * Created by Enzo Cotter on 2021/7/13.
 */
public class MyEditor extends AbstractButton implements TableCellRenderer, TableCellEditor, ActionListener {

    private JButton button;
    private JTable table;
    private int row;
    private int colmun;
    private String uname;

    public MyEditor(JTable table, String uname) {
        this.button = new JButton("评估");
        this.table = table;
        button.addActionListener(this);
        this.uname = uname;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println(table.getValueAt(row, 4));//测试，打印身份证号
        String id_number = (String) table.getValueAt(row, 4);//获取身份证号
        EstimateFrame.run(id_number, uname);//打开评估界面

    }

    @Override
    public Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1) {
        this.row = i;
        this.colmun = i1;
        return button;
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
}
