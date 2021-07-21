package com.careSystem.view;

import com.careSystem.pojo.Worker;
import com.careSystem.service.WorkService;
import com.careSystem.utils.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateWorkFrame extends JFrame {

    private static UpdateWorkFrame instance;
    private JPanel contentPane;
    private JPasswordField oldpwdField;
    private JPasswordField newpwdField;
    private WorkService workService = (WorkService) Util.getObject("work.service");

    /**
     * Create the frame.
     */
    private UpdateWorkFrame(String username) {

        Worker worker = workService.findByUsername(username);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 550);
        setTitle(worker.getName() + "的个人信息");
        ImageIcon frameIcon = new ImageIcon("./img/my.png");
        setIconImage(frameIcon.getImage());
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbloldpwd = new JLabel("原密码");
        lbloldpwd.setHorizontalAlignment(SwingConstants.CENTER);
        lbloldpwd.setBounds(76, 88, 58, 15);
        contentPane.add(lbloldpwd);

        JLabel lblnewpwd = new JLabel("新密码");
        lblnewpwd.setHorizontalAlignment(SwingConstants.CENTER);
        lblnewpwd.setBounds(76, 134, 58, 15);
        contentPane.add(lblnewpwd);

        JButton btnIsPwdVisible = new JButton("显");
        btnIsPwdVisible.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        btnIsPwdVisible.setBounds(330, 125, 36, 27);
        contentPane.add(btnIsPwdVisible);
        btnIsPwdVisible.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (newpwdField.getEchoChar() != (char) 0) {
                    newpwdField.setEchoChar((char) 0);
                    btnIsPwdVisible.setText("隐");
                } else {
                    newpwdField.setEchoChar('●');
                    btnIsPwdVisible.setText("显");
                }

            }
        });

        oldpwdField = new JPasswordField();
        oldpwdField.setBounds(198, 85, 108, 21);
        contentPane.add(oldpwdField);

        newpwdField = new JPasswordField();
        newpwdField.setBounds(198, 131, 108, 21);
        contentPane.add(newpwdField);

        JLabel lblname = new JLabel("姓名");
        lblname.setHorizontalAlignment(SwingConstants.CENTER);
        lblname.setBounds(76, 186, 58, 15);
        contentPane.add(lblname);

        JTextField textFieldname = new JTextField();
        textFieldname.setText(worker.getName());
        textFieldname.setBounds(198, 183, 108, 21);
        contentPane.add(textFieldname);
        textFieldname.setColumns(10);

        JLabel lblbirthday = new JLabel("生日");
        lblbirthday.setHorizontalAlignment(SwingConstants.CENTER);
        lblbirthday.setBounds(76, 238, 58, 15);
        contentPane.add(lblbirthday);

        JTextField textFieldbirth = new JTextField();
        textFieldbirth.setText(worker.getBirthday());
        textFieldbirth.setBounds(198, 235, 108, 21);
        contentPane.add(textFieldbirth);
        textFieldbirth.setColumns(10);

        JLabel lblspecical = new JLabel("专长");
        lblspecical.setHorizontalAlignment(SwingConstants.CENTER);
        lblspecical.setBounds(76, 285, 58, 15);
        contentPane.add(lblspecical);

        JTextField textFieldspecical = new JTextField();
        textFieldspecical.setText(worker.getSpeciality());
        textFieldspecical.setBounds(198, 282, 108, 21);
        contentPane.add(textFieldspecical);
        textFieldspecical.setColumns(10);

        JLabel lblprofession = new JLabel("职业");
        lblprofession.setHorizontalAlignment(SwingConstants.CENTER);
        lblprofession.setBounds(76, 330, 58, 15);
        contentPane.add(lblprofession);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"医生", "护士", "护工"}));
        comboBox.setBounds(198, 326, 93, 23);
        contentPane.add(comboBox);

        JLabel lblphone = new JLabel("电话");
        lblphone.setHorizontalAlignment(SwingConstants.CENTER);
        lblphone.setBounds(76, 375, 58, 15);
        contentPane.add(lblphone);

        JTextField textFieldphone = new JTextField();
        textFieldphone.setText(worker.getPhone());
        textFieldphone.setBounds(198, 372, 108, 21);
        contentPane.add(textFieldphone);
        textFieldphone.setColumns(10);

        JButton btnsava = new JButton("确认");
        btnsava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldpwd = new String(oldpwdField.getPassword());
                String newpwd = new String(newpwdField.getPassword());
                if (oldpwd.equals("")) {
                    JOptionPane.showMessageDialog(null, "密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    oldpwdField.requestFocus();
                } else if (!oldpwd.equals(worker.getPassword())) {
                    JOptionPane.showMessageDialog(null, "原密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                    oldpwdField.requestFocus();
                } else if (newpwd.equals("")) {
                    JOptionPane.showMessageDialog(null, "新密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    newpwdField.requestFocus();
                } else if (oldpwd.contains(" ")) {
                    JOptionPane.showMessageDialog(null, "密码不能包含空格", "错误", JOptionPane.ERROR_MESSAGE);
                    oldpwdField.requestFocus();
                } else if (newpwd.contains(" ")) {
                    JOptionPane.showMessageDialog(null, "新密码不能包含空格", "错误", JOptionPane.ERROR_MESSAGE);
                    newpwdField.requestFocus();
                } else {
                    worker.setPhone(textFieldphone.getText());
                    worker.setBirthday(textFieldbirth.getText());
                    worker.setName(textFieldname.getText());
                    worker.setPassword(newpwd);
                    worker.setSpeciality(textFieldspecical.getText());
                    worker.setProfession((String) comboBox.getSelectedItem());
                }
                Boolean re = workService.updateMy(worker);
                if (re) {
                    JOptionPane.showMessageDialog(null, "修改个人信息成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            }
        });
        btnsava.setBounds(82, 433, 97, 23);
        contentPane.add(btnsava);

        JButton btncancle = new JButton("取消");
        btncancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        btncancle.setBounds(251, 433, 97, 23);
        contentPane.add(btncancle);
    }

    /**
     * 获取单例的UpdateFrame
     */
    public static UpdateWorkFrame getInstance(String username) {
        if (instance == null) {
            instance = new UpdateWorkFrame(username);
        }
        return instance;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateWorkFrame frame = new UpdateWorkFrame("巍峨");
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void run(String username) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateWorkFrame frame = getInstance(username);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
