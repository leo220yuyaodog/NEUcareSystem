package com.careSystem.view;

import com.careSystem.pojo.FormWork;
import com.careSystem.service.FormService;
import com.careSystem.utils.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AddFormWork extends JFrame {

    private String type;
    private JPanel contentPane;
    private JTextField textFieldname;
    private FormService formService = (FormService) Util.getObject("form.service");

    /**
     * Create the frame.
     */
    public AddFormWork() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 359, 416);
        setTitle("新增模板");
        ImageIcon frameIcon = new ImageIcon("./img/formwork.png");
        setIconImage(frameIcon.getImage());
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbltype = new JLabel("选择模板类型");
        lbltype.setBounds(66, 142, 102, 32);
        contentPane.add(lbltype);

        JComboBox<String> comboBox = new JComboBox<>();

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {

                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    type = (String) comboBox.getSelectedItem();
                }

            }
        });
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"A", "B", "C"}));
        comboBox.setBounds(203, 142, 60, 32);
        comboBox.setSelectedIndex(0);
        contentPane.add(comboBox);

        JButton btnsave = new JButton("确定");
        btnsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = textFieldname.getText();

                if (name.equals("")) {
                    JOptionPane.showMessageDialog(null, "模板名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textFieldname.requestFocus();
                } else {
                    type = (String) comboBox.getSelectedItem();
                    Boolean re = formService.add(new FormWork(1, name, type));//返回添加结果
                    if (re) {
                        JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        FormWorkList.run();
                    } else {
                        JOptionPane.showMessageDialog(null, "模板名重复，添加失败", "错误", JOptionPane.ERROR_MESSAGE);
                        textFieldname.requestFocus();
                    }
                }

            }
        });
        btnsave.setBounds(125, 308, 97, 23);
        contentPane.add(btnsave);

        textFieldname = new JTextField();//模板名
        textFieldname.setBounds(203, 84, 66, 21);
        contentPane.add(textFieldname);
        textFieldname.setColumns(10);

        JLabel lblname = new JLabel("模板名称");
        lblname.setBounds(77, 87, 58, 15);
        contentPane.add(lblname);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddFormWork frame = new AddFormWork();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
