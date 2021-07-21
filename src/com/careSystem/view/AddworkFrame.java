package com.careSystem.view;

import com.careSystem.pojo.Worker;
import com.careSystem.service.WorkService;
import com.careSystem.utils.IconHandler;
import com.careSystem.utils.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddworkFrame extends JFrame {

    private final JTextField textFielduname;
    private final JPasswordField textPwdField;
    private final JTextField textFieldname;
    private final JTextField textFieldbirth;
    private final JTextField textFieldspecical;
    private final JTextField textFieldphone;
    private final WorkService workService = (WorkService) Util.getObject("work.service");

    /**
     * Create the frame.
     */
    public AddworkFrame() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 533);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("新建员工");//设置标题
        ImageIcon frameIcon = new ImageIcon("./img/my.png");
        setIconImage(frameIcon.getImage());//设置图标

        addWindowListener(new WindowAdapter() {//关闭窗口事件
            public void windowClosing(WindowEvent e) {
                ManagerFrame.main(null);
            }
        });

        JLabel lbluname = new JLabel("用户名");
        lbluname.setHorizontalAlignment(SwingConstants.CENTER);
        lbluname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        lbluname.setIcon(IconHandler.resizeIcon("./img/my.png"));
        lbluname.setBounds(76, 63, 78, 20);
        contentPane.add(lbluname);

        textFielduname = new JTextField();
        textFielduname.setBounds(184, 60, 107, 21);
        contentPane.add(textFielduname);
        textFielduname.setColumns(12);

        JLabel lblpwd = new JLabel("密码");
        lblpwd.setHorizontalAlignment(SwingConstants.CENTER);
        lblpwd.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        lblpwd.setIcon(IconHandler.resizeIcon("./img/password.png"));
        lblpwd.setBounds(76, 105, 78, 21);
        contentPane.add(lblpwd);

        textPwdField = new JPasswordField();
        textPwdField.setBounds(184, 108, 107, 21);
        contentPane.add(textPwdField);
        textPwdField.setColumns(12);

        JLabel lblname = new JLabel("姓名");
        lblname.setHorizontalAlignment(SwingConstants.CENTER);
        lblname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        lblname.setIcon(IconHandler.resizeIcon("./img/name.png"));
        lblname.setBounds(76, 165, 78, 20);
        contentPane.add(lblname);

        textFieldname = new JTextField("");
        textFieldname.setBounds(184, 165, 107, 21);
        contentPane.add(textFieldname);
        textFieldname.setColumns(10);

        JLabel lblbirthday = new JLabel("生日");
        lblbirthday.setHorizontalAlignment(SwingConstants.CENTER);
        lblbirthday.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        lblbirthday.setIcon(IconHandler.resizeIcon("./img/birth.png"));
        lblbirthday.setBounds(76, 225, 78, 20);
        contentPane.add(lblbirthday);

        textFieldbirth = new JTextField();
        textFieldbirth.addFocusListener(new JTextFieldHintListener(textFieldbirth,"格式2012-03-14"));
        textFieldbirth.setBounds(184, 225, 107, 21);
        contentPane.add(textFieldbirth);
        textFieldbirth.setColumns(10);

        JLabel lblspecical = new JLabel("专长");
        lblspecical.setHorizontalAlignment(SwingConstants.CENTER);
        lblspecical.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        lblspecical.setIcon(IconHandler.resizeIcon("./img/special.png"));
        lblspecical.setBounds(76, 280, 78, 20);
        contentPane.add(lblspecical);

        textFieldspecical = new JTextField();
        textFieldspecical.setBounds(184, 280, 107, 21);
        contentPane.add(textFieldspecical);
        textFieldspecical.setColumns(10);

        JLabel lblprofession = new JLabel("职业");
        lblprofession.setHorizontalAlignment(SwingConstants.CENTER);
        lblprofession.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        lblprofession.setIcon(IconHandler.resizeIcon("./img/profession.png"));
        lblprofession.setBounds(76, 330, 78, 20);
        contentPane.add(lblprofession);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"医生", "护士", "护工"}));
        comboBox.setBounds(184, 326, 77, 23);
        contentPane.add(comboBox);

        JLabel lblphone = new JLabel("电话");
        lblphone.setHorizontalAlignment(SwingConstants.CENTER);
        lblphone.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        lblphone.setIcon(IconHandler.resizeIcon("./img/phone.png"));
        lblphone.setBounds(76, 375, 78, 20);
        contentPane.add(lblphone);

        textFieldphone = new JTextField();
        textFieldphone.addFocusListener(new JTextFieldHintListener(textFieldphone,"输入手机号码11位"));
        textFieldphone.setToolTipText("输入11位电话号码");
        textFieldphone.setBounds(184, 372, 107, 21);
        contentPane.add(textFieldphone);
        textFieldphone.setColumns(10);

        JButton btnsava = new JButton("确认");
        btnsava.addActionListener(e -> {
            String uname = textFielduname.getText();//获取用户名
            String pwd = new String(textPwdField.getPassword());//密码
            String phone = textFieldphone.getText();//电话号
            String name = textFieldname.getText();//姓名
            String birth = textFieldbirth.getText();//生日
            String speciality = textFieldspecical.getText();
            if (uname.equals("")) {
                JOptionPane.showMessageDialog(null, "用户名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                textFielduname.requestFocus();
            } else if (pwd.equals("")) {
                JOptionPane.showMessageDialog(null, "密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                textPwdField.requestFocus();
            } else if (uname.contains(" ")) {
                JOptionPane.showMessageDialog(null, "用户名不能包含空格", "错误", JOptionPane.ERROR_MESSAGE);
                textFielduname.requestFocus();
            } else if (!uname.matches("^[\\w\\-－＿[０-９]\u4e00-\u9fa5\uFF21-\uFF3A\uFF41-\uFF5A]+$")) {
                JOptionPane.showMessageDialog(null, "用户名格式错误", "错误", JOptionPane.ERROR_MESSAGE);
                textFielduname.requestFocus();
            } else if (!pwd.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$")) {
                JOptionPane.showMessageDialog(null, "密码需为不少于6位数字字母组合", "错误", JOptionPane.ERROR_MESSAGE);
                textPwdField.requestFocus();
            } else if (!name.matches("^[\u4e00-\u9fa5.·]*$")) {
                JOptionPane.showMessageDialog(null, "输入中文姓名", "错误", JOptionPane.ERROR_MESSAGE);
                textFieldname.requestFocus();
            } else if (birth.equals("")) {
                JOptionPane.showMessageDialog(null, "请输入出生日期", "错误", JOptionPane.ERROR_MESSAGE);
                textFieldbirth.requestFocus();
            } else if (!birth.matches("(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)   \n" +
                    "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29) ")){
                JOptionPane.showMessageDialog(null, "请输入正确格式出生日期", "错误", JOptionPane.ERROR_MESSAGE);
                textFieldbirth.requestFocus();
            }else if (speciality.equals("")) {
                JOptionPane.showMessageDialog(null, "请输入专长", "错误", JOptionPane.ERROR_MESSAGE);
                textFieldspecical.requestFocus();
            } else if (!phone.matches("1\\d{10}")) {
                JOptionPane.showMessageDialog(null, "请输入正确手机号", "错误", JOptionPane.ERROR_MESSAGE);
                textFieldphone.requestFocus();
            } else {

                String profession = (String) comboBox.getSelectedItem();

                Worker worker = new Worker(1, uname, pwd, name, profession, birth, speciality, phone);
                Boolean re = workService.add(worker);//返回添加结果
                if (re) {//根据返回结果经行对应操作
                    JOptionPane.showMessageDialog(null, "注册成功\n您的用户序号为：" + worker.getId(),
                            "提示", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    ManagerFrame.main(null);
                } else {
                    JOptionPane.showMessageDialog(null, "账号重复，注册失败", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnsava.setBounds(57, 433, 97, 23);
        contentPane.add(btnsava);

        //取消按钮
        JButton btncancle = new JButton("取消");
        btncancle.addActionListener(actionEvent -> {
            dispose();
            ManagerFrame.main(null);
        });
        btncancle.setBounds(214, 433, 97, 23);
        contentPane.add(btncancle);

        JLabel lblNewLabel = new JLabel("汉字、字母、数字");
        lblNewLabel.setBounds(184, 83, 107, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("6-16位数字和字母组合");
        lblNewLabel_1.setBackground(Color.WHITE);
        lblNewLabel_1.setBounds(184, 139, 127, 15);
        contentPane.add(lblNewLabel_1);

        JButton btnNewButton = new JButton("显");
        btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        btnNewButton.addActionListener(e -> {

            if (textPwdField.getEchoChar() != (char) 0) {
                textPwdField.setEchoChar((char) 0);//设置密码为可见
                btnNewButton.setText("隐");
            } else {
                textPwdField.setEchoChar('●');//设置密码隐藏
                btnNewButton.setText("显");
            }

        });
        btnNewButton.setBounds(304, 106, 38, 29);
        contentPane.add(btnNewButton);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddworkFrame frame = new AddworkFrame();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
