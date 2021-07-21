package com.careSystem.view;

import com.careSystem.pojo.FQ_Link;
import com.careSystem.service.QuesService;
import com.careSystem.service.impl.FQLinkServiceImpl;
import com.careSystem.tables.QuestionTable;
import com.careSystem.utils.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class QuestionFrame extends JFrame {

    QuesService quesService = (QuesService) Util.getObject("question.service");
    private JPanel contentPane;
    private JTextField textField;
    private JTable table;

    /**
     * Create the frame.
     */
    public QuestionFrame(Integer formId) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 671, 455);
        setTitle("问题面板");
        ImageIcon frameIcon = new ImageIcon("./img/quiz.png");
        setIconImage(frameIcon.getImage());
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(137, 43, 66, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("搜索");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setModel(new QuestionTable(textField.getText()));
                table.getColumnModel().getColumn(4).setCellRenderer(new MyquestionEditor(table));
                table.getColumnModel().getColumn(4).setCellEditor(new MyquestionEditor(table));
            }
        });
        btnNewButton.setBounds(223, 42, 66, 23);
        contentPane.add(btnNewButton);

        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(52, 42, 60, 23);
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"A", "B", "C"}));
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("选中: " + comboBox.getSelectedIndex() + " = " + comboBox.getSelectedItem());
                    table.setModel(new QuestionTable((String) comboBox.getSelectedItem(), 1));
                    table.getColumnModel().getColumn(4).setCellRenderer(new MyquestionEditor(table));
                    table.getColumnModel().getColumn(4).setCellEditor(new MyquestionEditor(table));
                }
            }
        });
        comboBox.setToolTipText("问题类型");
        contentPane.add(comboBox);

        JButton btndelete = new JButton("删除");
        btndelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> deleteId = new ArrayList<>();
                for (int i = 0; i < table.getRowCount(); i++) {//遍历表格行
                    System.out.println(table.getValueAt(i, 0));
                    if (((Boolean) table.getValueAt(i, 0))) {
                        deleteId.add((Integer) table.getValueAt(i, 1));
                    }
                }
                FQLinkServiceImpl.getInstance().deleteLinkByQuestionId(deleteId);//删除问题对应模板关系
                quesService.delete(deleteId);//删除问题
                table.setModel(new QuestionTable());
                table.getColumnModel().getColumn(4).setCellRenderer(new MyquestionEditor(table));
                table.getColumnModel().getColumn(4).setCellEditor(new MyquestionEditor(table));
            }
        });
        btndelete.setBounds(52, 75, 81, 23);
        contentPane.add(btndelete);


        JButton btnadd = new JButton("新增");
        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddQuestionFrame.main(null);
            }
        });
        btnadd.setBounds(546, 75, 81, 23);
        contentPane.add(btnadd);

        table = new JTable(new QuestionTable());
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();//单元格渲染器
        tcr.setHorizontalAlignment(JLabel.CENTER);//居中显示
        table.setDefaultRenderer(table.getColumnClass(1), tcr);//设置渲染器
        table.setDefaultRenderer(table.getColumnClass(3), tcr);//设置渲染器
        table.getColumnModel().getColumn(4).setCellRenderer(new MyquestionEditor(table));
        table.getColumnModel().getColumn(4).setCellEditor(new MyquestionEditor(table));

        contentPane.add(table);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(37, 123, 590, 244);
        contentPane.add(scrollPane);

        JButton btnreturn = new JButton("返回");
        btnreturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (formId != 0) {
                    dispose();
                    FormWorkFrame.run(formId);
                } else {
                    dispose();
                }

            }
        });
        btnreturn.setBounds(425, 385, 97, 23);
        contentPane.add(btnreturn);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(36, 123, 591, 244);
        contentPane.add(tabbedPane);

        JButton btnaddlink = new JButton("添加");
//        if (formId == 0) {
//            btnaddlink.setEnabled(false);
//            ImageIcon idIconSource = new ImageIcon("./img/disabled.png");
//            btnaddlink.setDisabledIcon(new ImageIcon(idIconSource.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
//        }
        btnaddlink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> questionIds = new ArrayList<>();
                List<FQ_Link> fq_links = new ArrayList<>();

                for (int i = 0; i < table.getRowCount(); i++) {
                    System.out.println(table.getValueAt(i, 0));
                    if (((Boolean) table.getValueAt(i, 0))) {
                        questionIds.add((Integer) table.getValueAt(i, 1));
                    }
                }
                for (Integer qId : questionIds) {
                    fq_links.add(new FQ_Link(formId, qId));
                }
                Boolean re = FQLinkServiceImpl.getInstance().add(fq_links);
                if (re) {
                    int select = JOptionPane.showConfirmDialog(null, "添加成功，确定返回模板？", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (select == 0) {
                        dispose();
                        FormWorkFrame.run(formId);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "添加失败", "提示", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnaddlink.setBounds(157, 385, 97, 23);
        contentPane.add(btnaddlink);

        JButton btnreset = new JButton("重置");
        btnreset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.setModel(new QuestionTable());
                table.getColumnModel().getColumn(4).setCellRenderer(new MyquestionEditor(table));
                table.getColumnModel().getColumn(4).setCellEditor(new MyquestionEditor(table));
            }
        });
        btnreset.setBounds(192, 74, 97, 23);
        contentPane.add(btnreset);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuestionFrame frame = new QuestionFrame(0);//专门为打开问题面板提供，传入0，无法进行给模板添加问题操作
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void run(Integer formId) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuestionFrame frame = new QuestionFrame(formId);//给模板添加问题时启用，传入对应模板id
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
