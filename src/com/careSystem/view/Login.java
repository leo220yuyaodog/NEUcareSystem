package com.careSystem.view;

import com.careSystem.service.WorkService;
import com.careSystem.service.impl.WorkServiceImpl;
import com.careSystem.utils.Util;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Login extends JFrame {

    private WorkService workService = (WorkServiceImpl) Util.getObject("work.service");
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private JLabel userIcon;
    private JLabel pwdIcon;
    private int index = 0;

    /**
     * Create the frame.
     */

    public Login() {
        FlatLightLaf.install();
        UIManager.put( "Component.focusWidth", 1 );
        UIManager.put( "Button.arc", 0 );
        UIManager.put( "Component.arc", 0 );
        UIManager.put( "CheckBox.arc", 0 );
        UIManager.put( "ProgressBar.arc", 0 );
        setIconImage(Toolkit.getDefaultToolkit().getImage("./img/title.png"));
        setTitle("颐养社区中心");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 520);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JLabel bglabel = new JLabel();//创建JLabel
        getBackgroundPicture(bglabel, contentPane, "./img/picture");//自定义方法在最后
        this.getLayeredPane().add(bglabel, Integer.valueOf(Integer.MIN_VALUE));//将背景标签添加到JFrame的LayeredPane中，我的方法继承了JFrame，所以这里使用了this
        contentPane.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int num = JOptionPane.showConfirmDialog(null, "你需要更换背景吗？",
                            "提示", JOptionPane.YES_NO_OPTION);
                    if (num == JOptionPane.YES_OPTION) {
                        //调用方法，更换背景
                        getBackgroundPicture(bglabel, contentPane, "./img/picture");
                    }
                }
            }
        });
        ImageIcon idIconSource = new ImageIcon("./img/uname.png");
        ImageIcon pwdIconSouse = new ImageIcon("./img/password.png");


        JPanel panel = new JPanel();
        panel.setBackground(new Color(89, 213, 231));
        panel.setBounds(304, 144, 320, 226);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel();
        ImageIcon background = new ImageIcon("./img/login.jpeg");
        label.setIcon(background);//将图片设置到Jlabel
        label.setBounds(304, 144, 320, 226);//设图片显示的区域
        label.setOpaque(false);
        panel.add(label);
        panel.setOpaque(false);
        this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));

        final JButton loginbutton = new JButton("登陆");
        loginbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                //鼠标指针离开标签
                loginbutton.setForeground(Color.BLACK);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //鼠标指针指向标签
                loginbutton.setForeground(Color.ORANGE);
            }
        });
        loginbutton.setForeground(new Color(0, 0, 0));
        loginbutton.setBackground(new Color(243, 242, 233));
        loginbutton.setBounds(111, 179, 103, 37);
        panel.add(loginbutton);
        loginbutton.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        JLabel lbpwd = new JLabel("密码");
        lbpwd.setBounds(24, 95, 69, 35);
        panel.add(lbpwd);
        lbpwd.setHorizontalAlignment(SwingConstants.CENTER);
        lbpwd.setFont(new Font("微软雅黑", Font.PLAIN, 20));


        JLabel lbuser = new JLabel("用户名");
        lbuser.setBounds(24, 35, 69, 35);
        panel.add(lbuser);
        lbuser.setHorizontalAlignment(SwingConstants.CENTER);
        lbuser.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        textField = new JTextField("");
        textField.setBounds(144, 39, 140, 35);
        textField.addFocusListener(new JTextFieldHintListener(textField,"输入用户名"));

        panel.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setEchoChar((char) 0);
        passwordField.setText("请输入密码");
        passwordField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                passwordField.setEchoChar('●');
                passwordField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getPassword().equals("")) {
                    textField.setForeground(Color.GRAY);
                    passwordField.setEchoChar((char) 0);
                    passwordField.setText("请输入密码");
                }
            }
        });
        passwordField.setBounds(144, 99, 140, 35);
        panel.add(passwordField);

        pwdIcon = new JLabel("");
        pwdIcon.setBounds(113, 95, 49, 45);
        panel.add(pwdIcon);
        pwdIcon.setIcon(new ImageIcon(pwdIconSouse.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));

        userIcon = new JLabel("");
        userIcon.setBounds(113, 35, 49, 45);
        panel.add(userIcon);
        userIcon.setIcon(new ImageIcon(idIconSource.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));


        loginbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String uname = textField.getText();
                String pwd = new String(passwordField.getPassword());
                if (uname.equals("")) {
                    JOptionPane.showMessageDialog(null, "用户名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textField.requestFocus();
                } else if (pwd.equals("")) {
                    JOptionPane.showMessageDialog(null, "密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    passwordField.requestFocus();
                } else {
                    int re = workService.login(uname, pwd);

                    switch (re) {
                        case 1://超级管理员登录
                            JOptionPane.showMessageDialog(null, "欢迎管理员登录", "提示", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            ManagerFrame.main(null);
                            break;
                        case 2://工作人员登录
                            JOptionPane.showMessageDialog(null, "欢迎登录", "提示", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            PatientFrame.run(uname);
                            break;
                        case -1: {//密码错误
                            JOptionPane.showMessageDialog(null, "密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                            textField.requestFocus();
                            textField.selectAll();
                            break;
                        }
                        case -2: {//用户名不存在
                            JOptionPane.showMessageDialog(null, "无此用户", "错误", JOptionPane.ERROR_MESSAGE);
                            passwordField.requestFocus();
                            break;
                        }

                    }
                }
            }
        });


    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //设置背景图片
    public void getBackgroundPicture(JLabel bglabel, JPanel contentPane, String pathname) {
        File file = new File(pathname);
        File[] list = file.listFiles();
        String path = list[index].getAbsolutePath();//获取路径

        ImageIcon background = new ImageIcon(path);
        index++;
        if (index >= list.length) {
            index = 0;
        }

        bglabel.setIcon(background);//将图片设置到Jlabel中

        bglabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());//设图片显示的区域
        contentPane.setOpaque(false);//设置面板为透明，在gbLabel之上的JPanel都要设置为透明
    }


}
