package com.careSystem.view;

import com.careSystem.pojo.Patient;
import com.careSystem.service.PatientService;
import com.careSystem.utils.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddpatientFrame extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldname;
    private JTextField textFieldage;
    private JTextField textFieldidnumber;
    private JTextField textFieldphone;
    private JTextField textFieldemergepeople;
    private JTextField textFieldemergencyphone;
    private PatientService patientService = (PatientService) Util.getObject("patient.service");

    /**
     * Create the frame.
     */
    public AddpatientFrame() {


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 533);
        setTitle("添加病人");
        ImageIcon imageIcon = new ImageIcon("./img/patient.png");
        setIconImage(imageIcon.getImage());
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblname = new JLabel("姓名");
        lblname.setBounds(57, 71, 97, 15);
        contentPane.add(lblname);

        textFieldname = new JTextField();
        textFieldname.setBounds(210, 68, 66, 21);
        contentPane.add(textFieldname);
        textFieldname.setColumns(10);

        JLabel lblage = new JLabel("年龄");
        lblage.setBounds(57, 116, 97, 15);
        contentPane.add(lblage);

        textFieldage = new JTextField();
        textFieldage.setBounds(210, 113, 66, 21);
        contentPane.add(textFieldage);
        textFieldage.setColumns(10);

        JLabel lbiidnumber = new JLabel("身份证号");
        lbiidnumber.setBounds(57, 220, 97, 15);
        contentPane.add(lbiidnumber);

        textFieldidnumber = new JTextField();
        textFieldidnumber.setBounds(210, 217, 66, 21);
        contentPane.add(textFieldidnumber);
        textFieldidnumber.setColumns(10);

        JLabel lblphone = new JLabel("联系电话");
        lblphone.setBounds(57, 268, 97, 15);
        contentPane.add(lblphone);

        textFieldphone = new JTextField();
        textFieldphone.setBounds(210, 268, 66, 21);
        contentPane.add(textFieldphone);
        textFieldphone.setColumns(10);

        JLabel lblemergence = new JLabel("紧急联系人");
        lblemergence.setBounds(57, 317, 97, 15);
        contentPane.add(lblemergence);

        textFieldemergepeople = new JTextField();
        textFieldemergepeople.setBounds(214, 314, 66, 21);
        contentPane.add(textFieldemergepeople);
        textFieldemergepeople.setColumns(10);

        JLabel lblemergephone = new JLabel("紧急联系人电话");
        lblemergephone.setBounds(57, 374, 97, 15);
        contentPane.add(lblemergephone);

        textFieldemergencyphone = new JTextField();
        textFieldemergencyphone.setBounds(214, 371, 66, 21);
        contentPane.add(textFieldemergencyphone);
        textFieldemergencyphone.setColumns(10);

        JLabel lblsex = new JLabel("性别");
        lblsex.setBounds(57, 170, 58, 15);
        contentPane.add(lblsex);

        JRadioButton rdbtnmale = new JRadioButton("男");
        rdbtnmale.setBounds(204, 166, 37, 23);
        contentPane.add(rdbtnmale);

        JRadioButton rdbtnfemale = new JRadioButton("女");
        rdbtnfemale.setBounds(253, 166, 37, 23);
        contentPane.add(rdbtnfemale);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnfemale);
        buttonGroup.add(rdbtnmale);

        JButton btnsava = new JButton("确认");
        btnsava.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textFieldname.getText();//获取输入值
                String age = textFieldage.getText();
                String id_number = textFieldidnumber.getText();
                String phone = textFieldphone.getText();
                String emerge_contact = textFieldemergepeople.getText();
                String emerge_phone = textFieldemergencyphone.getText();
                String sex;
                if (rdbtnfemale.isSelected()) {
                    sex = "男";
                } else sex = "女";

                if (name.equals("")) {
                    JOptionPane.showMessageDialog(null, "姓名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textFieldname.requestFocus();
                } else if (!name.matches("^[\u4e00-\u9fa5.·]*$")) {
                    JOptionPane.showMessageDialog(null, "输入中文姓名", "错误", JOptionPane.ERROR_MESSAGE);
                    textFieldname.requestFocus();
                } else if (age.equals("")) {
                    JOptionPane.showMessageDialog(null, "年龄不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textFieldage.requestFocus();
                } else if (id_number.equals("")) {
                    JOptionPane.showMessageDialog(null, "身份证号不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textFieldidnumber.requestFocus();
                } else if (!id_number.matches("(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                        "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)")) {
                    JOptionPane.showMessageDialog(null, "请填写正确身份证号", "错误", JOptionPane.ERROR_MESSAGE);
                    textFieldidnumber.requestFocus();
                } else if (phone.equals("")) {
                    JOptionPane.showMessageDialog(null, "电话号不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textFieldphone.requestFocus();
                } else if (!phone.matches("1\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "请输入正确手机号", "错误", JOptionPane.ERROR_MESSAGE);
                    textFieldphone.requestFocus();
                } else if (emerge_contact.equals("")) {
                    JOptionPane.showMessageDialog(null, "紧急联系人不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textFieldemergepeople.requestFocus();
                } else if (emerge_phone.equals("")) {
                    JOptionPane.showMessageDialog(null, "紧急联系电话不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textFieldemergencyphone.requestFocus();
                }else if (!phone.matches("1\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "请输入正确手机号", "错误", JOptionPane.ERROR_MESSAGE);
                    textFieldemergencyphone.requestFocus();
                } else {
                    Patient patient = new Patient(1, name, age, sex, phone, emerge_phone, emerge_contact, id_number);//创建一个病人对象
                    System.out.println(patientService.findAll());//测试
                    Boolean re = patientService.add(patient);//添加病人
                    if (re) {
                        JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);//添加成功，提示
                        dispose();
                    }
                }
            }
        });
        btnsava.setBounds(57, 433, 97, 23);
        contentPane.add(btnsava);

        JButton btncancle = new JButton("取消");
        btncancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        btncancle.setBounds(214, 433, 97, 23);
        contentPane.add(btncancle);

    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddpatientFrame frame = new AddpatientFrame();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


