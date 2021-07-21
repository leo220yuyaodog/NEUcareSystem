package com.careSystem.view;

import com.careSystem.pojo.Patient;
import com.careSystem.pojo.Worker;
import com.careSystem.service.PatientService;
import com.careSystem.service.WorkService;
import com.careSystem.tables.PatientTable;
import com.careSystem.utils.Util;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.List;

public class PatientFrame extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private String username;
    private PatientService patientService = (PatientService) Util.getObject("patient.service");
    private WorkService workService = (WorkService) Util.getObject("work.service");

    /**
     * Create the frame.
     */
    private PatientFrame(String username) {
        FlatLightLaf.install();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 850, 530);
        setIconImage(Toolkit.getDefaultToolkit().getImage("./img/title.png"));
        setTitle("颐养社区中心");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        this.username = username;
        Worker worker = workService.findByUsername(username);//根据传入用户名找到对应员工
        if (worker == null)//测试时使用，避免出现空指针
            worker = new Worker();

        JLabel labelProfession = new JLabel(worker.getProfession());
        labelProfession.setBounds(533, 35, 65, 30);
        ImageIcon usericon = new ImageIcon("./img/user1.png");
        labelProfession.setIcon(new ImageIcon(usericon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        JLabel labelName = new JLabel(worker.getName());
        labelName.setBounds(600, 35, 58, 30);
        contentPane.add(labelProfession);
        contentPane.add(labelName);

        table = new JTable(new PatientTable());
        render();
        table.setBounds(67, 118, 710, 307);

        contentPane.add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(51, 116, 751, 322);
        contentPane.add(scrollPane);

        JButton deletebtn = new JButton("删除");
        deletebtn.addActionListener(new ActionListener() {
            String[] deletePatients = new String[0];

            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < table.getRowCount(); i++) {
//                    System.out.println(table.getValueAt(i, 0));
                    if (((Boolean) table.getValueAt(i, 0))) {
                        //扩展1个长度
                        deletePatients = Arrays.copyOf(deletePatients, deletePatients.length + 1);
                        //将数据保存在末尾
                        deletePatients[deletePatients.length - 1] = (String) table.getValueAt(i, 1);
//                        System.out.println(Arrays.toString(deletePatients));
                    }
                }
                patientService.delete(deletePatients);
//                dispose();
//                ManagerFrame.main(null);
                refreshTable();
            }
        });
        deletebtn.setBounds(67, 76, 97, 30);
        contentPane.add(deletebtn);

        JButton addbtn = new JButton("添加病人");
        addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddpatientFrame.main(null);
                refreshTable();
            }
        });
        addbtn.setBounds(174, 76, 97, 30);
        contentPane.add(addbtn);

        JButton btnNewButton_2 = new JButton("修改个人信息");
        btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UpdateWorkFrame.run(username);
            }
        });
        btnNewButton_2.setBounds(673, 35, 120, 30);
        contentPane.add(btnNewButton_2);

        JButton searchbtn = new JButton("模板列表");
        searchbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FormWorkList.main(null);
            }
        });
        searchbtn.setBounds(559, 76, 97, 30);
        contentPane.add(searchbtn);

        textField = new JTextField("输入姓名");
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setText("");
                textField.setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().equals("")) {
                    textField.setText("输入姓名");
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        textField.setBounds(67, 36, 97, 30);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnsearch = new JButton("姓名查询");
        btnsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTable();
            }
        });
        btnsearch.setBounds(174, 35, 97, 30);
        contentPane.add(btnsearch);


        JButton savebtn = new JButton("保存");
        savebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Patient> list = patientService.findAll();
                System.out.println(list);
                if (list != null && !list.isEmpty() && table.getRowCount() != 0) {
                    for (Patient pp : list) {
                        for (int i = 0; i < table.getRowCount(); i++) {
//                            {"", "姓名", "年龄", "性别", "身份证", "联系电话", "紧急联系电话", "紧急联系人", ""};
                            if (pp.getId_number().equals(table.getValueAt(i, 4))) {//确定身份证号，进行匹配
                                //匹配成功，修改属性
                                pp.setName((String) table.getValueAt(i, 1));
                                pp.setAge((String) table.getValueAt(i, 2));
                                pp.setSex((String) table.getValueAt(i, 3));
                                pp.setPhone((String) table.getValueAt(i, 5));
                                pp.setEmergency_number((String) table.getValueAt(i, 6));
                                pp.setEmergency_contact((String) table.getValueAt(i, 7));
                            }
                        }
                    }
                    System.out.println(list);
                    patientService.updatePatient(list);//（保存）调用方法，修改文件数据
                    refreshTable();
                }
            }
        });
        savebtn.setBounds(235, 448, 97, 35);
        contentPane.add(savebtn);

        JButton returnbtn = new JButton("返回");
        returnbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                Login.main(null);
            }
        });
        returnbtn.setBounds(523, 448, 97, 35);
        contentPane.add(returnbtn);

        JButton btnquestion = new JButton("问题面板");
        btnquestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                QuestionFrame.main(null);
            }
        });
        btnquestion.setBounds(673, 76, 120, 30);
        contentPane.add(btnquestion);

        JLabel lblNewLabel = new JLabel("病患管理系统");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 24));
        lblNewLabel.setBounds(298, 0, 215, 35);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("重置");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.setModel(new PatientTable());
                render();
            }
        });
        btnNewButton.setBounds(281, 76, 86, 30);
        contentPane.add(btnNewButton);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PatientFrame frame = new PatientFrame(" ");
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
                    PatientFrame frame = new PatientFrame(username);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    /**
     * @Description: 刷新表格
     * @date 2021/7/20 21:51
     * @return void
     */
    public void refreshTable() {

        table.setModel(new PatientTable());
        render();
    }
    /**
     * @Description: 搜素后表格
     * @date 2021/7/20 21:52
     * @return void
     */
    public void searchTable() {

        table.setModel(new PatientTable(textField.getText()));
        render();
    }

    public void render() {
        table.getColumnModel().getColumn(8).setCellRenderer(new MyEditor(table, username));//渲染，添加按钮
        table.getColumnModel().getColumn(8).setCellEditor(new MyEditor(table, username));

        table.getColumnModel().getColumn(4).setPreferredWidth(130);//设置列宽
        table.getColumnModel().getColumn(5).setPreferredWidth(85);
        table.getColumnModel().getColumn(6).setPreferredWidth(85);
    }
}
