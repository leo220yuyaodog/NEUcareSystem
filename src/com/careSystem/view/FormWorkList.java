package com.careSystem.view;

import com.careSystem.service.FormService;
import com.careSystem.service.impl.FQLinkServiceImpl;
import com.careSystem.tables.FormTable;
import com.careSystem.utils.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class FormWorkList extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private FormService formService = (FormService) Util.getObject("form.service");

    /**
     * Create the frame.
     */
    public FormWorkList() {
        setTitle("模板管理");
        ImageIcon frameIcon = new ImageIcon("./img/formwork.png");
        setIconImage(frameIcon.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 640, 392);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton seebtn = new JButton("预览");
        seebtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;//第一列为复选框
                int cnt = 0;//统计选择个数
                int row = 0; //记录选择的行
                for (int i = 0; i < table.getRowCount(); i++) {
                    if ((Boolean) table.getValueAt(i, column)) {
                        cnt++;
                        row = i;
                    }
                }
                if (cnt == 0) {
                    JOptionPane.showMessageDialog(null, "未选择模板", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else if (cnt > 1) {
                    JOptionPane.showMessageDialog(null, "不能选择多个模板预览", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Integer id = (Integer) table.getValueAt(row, 1);//获得模板id
                    dispose();
                    FormWorkFrame.run(id);
                }

            }
        });
        seebtn.setBounds(30, 63, 88, 23);
        contentPane.add(seebtn);

        JButton deletebtn = new JButton("删除");
        deletebtn.addActionListener(new ActionListener() {
            List<Integer> deleteId = new ArrayList<>();

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (int i = 0; i < table.getRowCount(); i++) {//遍历所有行
                    System.out.println(table.getValueAt(i, 0));//测试，打印 复选框值
                    if (((Boolean) table.getValueAt(i, 0))) {
                        deleteId.add((Integer) table.getValueAt(i, 1));//如果复选框选择，添加删除id
                    }
                }
                formService.delete(deleteId);//删除问题
                FQLinkServiceImpl.getInstance().deleteLinkByFormId(deleteId);//删除问题模板关联
                table.setModel(new FormTable());//刷新表格
            }
        });
        deletebtn.setBounds(154, 63, 88, 23);
        contentPane.add(deletebtn);

        JButton addbtn = new JButton("新建模板");
        addbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddFormWork.main(null);
            }
        });
        addbtn.setBounds(477, 63, 97, 23);
        contentPane.add(addbtn);

        iniTable();

        JButton resetbtn = new JButton("刷新");
        resetbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                table.setModel(new FormTable());
            }
        });
        resetbtn.setBounds(154, 326, 97, 23);
        contentPane.add(resetbtn);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"A", "B", "C"}));
        comboBox.setToolTipText("");
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("选中: " + comboBox.getSelectedIndex() + " = " + comboBox.getSelectedItem());
                    table.setModel(new FormTable((String) comboBox.getSelectedItem()));
                }
            }
        });
        comboBox.setBounds(30, 10, 88, 23);
        contentPane.add(comboBox);

        JLabel lbltype = new JLabel("模板类型");
        lbltype.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lbltype.setBounds(128, 14, 70, 15);
        contentPane.add(lbltype);

        JButton returnbtn = new JButton("返回");
        returnbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        returnbtn.setBounds(345, 326, 97, 23);
        contentPane.add(returnbtn);

    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormWorkList frame = new FormWorkList();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void run() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormWorkList frame = new FormWorkList();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void iniTable() {
        table = new JTable(new FormTable());
        table.setBounds(30, 103, 544, 213);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();//单元格渲染器
        tcr.setHorizontalAlignment(JLabel.CENTER);//居中显示
        table.setDefaultRenderer(table.getColumnClass(1), tcr);//设置渲染器
        table.setDefaultRenderer(table.getColumnClass(3), tcr);//设置渲染器
        contentPane.add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 103, 544, 213);
        contentPane.add(scrollPane);
    }
}
