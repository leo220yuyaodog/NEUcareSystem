package com.careSystem.view;

import com.careSystem.pojo.Worker;
import com.careSystem.service.WorkService;
import com.careSystem.tables.WorkTable;
import com.careSystem.utils.Util;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class ManagerFrame extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private WorkService workService = (WorkService) Util.getObject("work.service");

    /**
     * Create the frame.
     */
    public ManagerFrame() {

        FlatLightLaf.install();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 850, 520);
        setIconImage(Toolkit.getDefaultToolkit().getImage("./img/title.png"));
        setTitle("颐养社区中心");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setText("输入姓名查询");
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setText("");
                textField.setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().equals("")) {
                    textField.setText("输入姓名查询");
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        textField.setBounds(67, 38, 97, 31);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton searchbtn = new JButton("姓名查询");
        searchbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String goal = textField.getText();
                table.setModel(new WorkTable(goal));

            }
        });
        searchbtn.setBounds(188, 38, 97, 31);
        contentPane.add(searchbtn);

        JComboBox worksbox = new JComboBox();
        worksbox.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        worksbox.setModel(new DefaultComboBoxModel(new String[]{"医生", "护士", "护工"}));
        worksbox.setToolTipText("职位");
        worksbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    table.setModel(new WorkTable((String) worksbox.getSelectedItem(), 1));
                    System.out.println(table.getModel().toString());
                }
            }
        });
        worksbox.setBounds(305, 38, 90, 31);
        contentPane.add(worksbox);

        JButton deletebtn = new JButton("删除");
        deletebtn.addActionListener(new ActionListener() {
            String[] deleteWorks = new String[0];

            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < table.getRowCount(); i++) {
                    System.out.println(table.getValueAt(i, 0));
                    if (((Boolean) table.getValueAt(i, 0))) {
                        //扩展1个长度
                        deleteWorks = Arrays.copyOf(deleteWorks, deleteWorks.length + 1);
                        //将数据保存在末尾
                        deleteWorks[deleteWorks.length - 1] = (String) table.getValueAt(i, 1);
                        System.out.println(Arrays.toString(deleteWorks));
                    }
                }
                workService.delete(deleteWorks);//删除
                refreshTable();//刷新表格
            }
        });
        deletebtn.setBounds(67, 79, 97, 32);
        contentPane.add(deletebtn);

        JButton addbtn = new JButton("添加工作人员");
        addbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddworkFrame.main(null);
            }
        });
        addbtn.setBounds(656, 79, 119, 32);
        contentPane.add(addbtn);

        initablle();//初始化表格


        JButton savebtn = new JButton("保存");
        savebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Worker> list = workService.findAll();
                System.out.println(list);
                if (list != null && !list.isEmpty()) {
                    for (Worker ww : list) {
                        for (int i = 0; i < table.getRowCount(); i++) {
                            if (ww.getUsername().equals(table.getValueAt(i, 1))) {//确定用户名，进行匹配
                                //匹配成功，修改属性
                                ww.setPassword((String) table.getValueAt(i, 2));
                                ww.setName((String) table.getValueAt(i, 3));
                                ww.setBirthday((String) table.getValueAt(i, 4));
                                ww.setSpeciality((String) table.getValueAt(i, 5));
                                ww.setProfession((String) table.getValueAt(i, 6));
                                ww.setPhone((String) table.getValueAt(i, 7));
                            }
                        }
                    }
//                    System.out.println(list);
                    workService.updateWorker(list);//（保存）调用方法，修改文件数据
                }
            }
        });
        savebtn.setBounds(202, 435, 97, 40);
        contentPane.add(savebtn);

        JButton returnbtn = new JButton("返回");
        returnbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login.main(null);//转入登录页面
            }
        });
        returnbtn.setBounds(517, 435, 97, 40);
        contentPane.add(returnbtn);

        JButton btnreset = new JButton("重置");
        btnreset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.setModel(new WorkTable());
            }
        });
        btnreset.setBounds(188, 84, 97, 23);
        contentPane.add(btnreset);

        JLabel lblNewLabel = new JLabel("工作人员管理系统");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 24));
        lblNewLabel.setBounds(271, 0, 266, 28);
        contentPane.add(lblNewLabel);


    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManagerFrame frame = new ManagerFrame();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void initablle() {
        table = new JTable(new WorkTable());
        table.setBounds(45, 51, 700, 400);
        contentPane.add(table);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(67, 121, 710, 304);
        contentPane.add(scrollPane);
    }

    void refreshTable() {
        table.setModel(new WorkTable());//刷新表格模型
    }
}
