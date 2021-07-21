package com.careSystem.view;

import com.careSystem.pojo.Question;
import com.careSystem.service.QuesService;
import com.careSystem.utils.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddQuestionFrame extends JFrame {

    private JPanel contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextArea textArea;
    private QuesService quesService = (QuesService) Util.getObject("question.service");

    /**
     * Create the frame.
     * 查看详情时的构造方法
     */
    public AddQuestionFrame(int id) {

        Question question = quesService.findByID(id);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 415, 515);
        setTitle("问题详情");
        ImageIcon frameIcon = new ImageIcon("./img/quiz.png");
        setIconImage(frameIcon.getImage());
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("类型");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(73, 86, 58, 15);
        contentPane.add(lblNewLabel);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"A", "B", "C"}));
        comboBox.setBounds(211, 82, 63, 23);
        comboBox.setSelectedItem(question.getType());
        contentPane.add(comboBox);

        JLabel lblquestion = new JLabel("问题");
        lblquestion.setBounds(73, 146, 58, 15);
        contentPane.add(lblquestion);

        textArea = new JTextArea();
        textArea.setBounds(79, 181, 232, 63);
        textArea.setText(question.getQuestion());
        contentPane.add(textArea);

        JLabel lblans1 = new JLabel("回答一");
        lblans1.setBounds(79, 279, 58, 15);
        contentPane.add(lblans1);

        JLabel lbans2 = new JLabel("回答二");
        lbans2.setBounds(79, 336, 58, 15);
        contentPane.add(lbans2);

        JLabel lblans3 = new JLabel("回答三");
        lblans3.setBounds(79, 392, 58, 15);
        contentPane.add(lblans3);

        textField1 = new JTextField();
        textField1.setText(question.getAnswers().get(0));
        textField1.setBounds(208, 276, 103, 21);
        contentPane.add(textField1);
        textField1.setColumns(10);

        textField2 = new JTextField();
        textField2.setText(question.getAnswers().get(1));
        textField2.setBounds(208, 333, 103, 21);
        contentPane.add(textField2);
        textField2.setColumns(10);

        textField3 = new JTextField();
        textField3.setText(question.getAnswers().get(2));
        textField3.setBounds(208, 389, 103, 21);
        contentPane.add(textField3);
        textField3.setColumns(10);

        JButton btnconfirm = new JButton("确认");
        btnconfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String question = textArea.getText();
                String ans1 = textField1.getText();
                String ans2 = textField2.getText();
                String ans3 = textField3.getText();

                if (question.equals("")) {
                    JOptionPane.showMessageDialog(null, "姓名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textArea.requestFocus();
                } else if (ans1.equals("")) {
                    JOptionPane.showMessageDialog(null, "年龄不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textField1.requestFocus();
                } else if (ans2.equals("")) {
                    JOptionPane.showMessageDialog(null, "身份证号不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textField2.requestFocus();
                } else if (ans3.equals("")) {
                    JOptionPane.showMessageDialog(null, "电话号不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textField3.requestFocus();
                } else {
                    String type = (String) comboBox.getSelectedItem();
                    List<String> answer = new ArrayList<>();
                    answer.add(ans1);
                    answer.add(ans2);
                    answer.add(ans3);

                    Boolean re = quesService.updateQuestion(new Question(id, question, answer, type));
                    JOptionPane.showMessageDialog(null, "修改问题成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    QuestionFrame.main(null);


                }
            }
        });
        btnconfirm.setBounds(79, 432, 97, 23);
        contentPane.add(btnconfirm);

        JButton btnreset = new JButton("重置");
        btnreset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
            }
        });
        btnreset.setBounds(62, 26, 97, 23);
        contentPane.add(btnreset);

        JButton btnreturn = new JButton("返回");
        btnreturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnreturn.setBounds(211, 432, 97, 23);
        contentPane.add(btnreturn);
    }

    /**
     * 新增问题时的构造方法
     */
    public AddQuestionFrame() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 415, 515);
        ImageIcon frameIcon = new ImageIcon("./img/quiz.png");
        setIconImage(frameIcon.getImage());//设置图标
        setTitle("新建问题");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("类型");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(73, 86, 58, 15);
        contentPane.add(lblNewLabel);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"A", "B", "C"}));
        comboBox.setBounds(211, 82, 63, 23);
        contentPane.add(comboBox);

        JLabel lblquestion = new JLabel("问题");
        lblquestion.setBounds(73, 146, 58, 15);
        contentPane.add(lblquestion);

        textArea = new JTextArea();
        textArea.setBounds(79, 181, 232, 63);
        contentPane.add(textArea);

        JLabel lblans1 = new JLabel("回答一");
        lblans1.setBounds(79, 279, 58, 15);
        contentPane.add(lblans1);

        JLabel lbans2 = new JLabel("回答二");
        lbans2.setBounds(79, 336, 58, 15);
        contentPane.add(lbans2);

        JLabel lblans3 = new JLabel("回答三");
        lblans3.setBounds(79, 392, 58, 15);
        contentPane.add(lblans3);

        textField1 = new JTextField();
        textField1.setBounds(208, 276, 103, 21);
        contentPane.add(textField1);
        textField1.setColumns(10);

        textField2 = new JTextField();
        textField2.setBounds(208, 333, 103, 21);
        contentPane.add(textField2);
        textField2.setColumns(10);

        textField3 = new JTextField();
        textField3.setBounds(208, 389, 103, 21);
        contentPane.add(textField3);
        textField3.setColumns(10);

        JButton btnconfirm = new JButton("确认");
        btnconfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String question = textArea.getText();
                String ans1 = textField1.getText();
                String ans2 = textField2.getText();
                String ans3 = textField3.getText();

                if (question.equals("")) {
                    JOptionPane.showMessageDialog(null, "姓名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textArea.requestFocus();
                } else if (ans1.equals("")) {
                    JOptionPane.showMessageDialog(null, "年龄不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textField1.requestFocus();
                } else if (ans2.equals("")) {
                    JOptionPane.showMessageDialog(null, "身份证号不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textField2.requestFocus();
                } else if (ans3.equals("")) {
                    JOptionPane.showMessageDialog(null, "电话号不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    textField3.requestFocus();
                } else {
                    String type = (String) comboBox.getSelectedItem();
                    List<String> answer = new ArrayList<>();//答案List
                    answer.add(ans1);//添加答案
                    answer.add(ans2);
                    answer.add(ans3);

                    Boolean re = quesService.add(new Question(question, answer, type));//添加问题
                    if (re) {
                        JOptionPane.showMessageDialog(null, "添加问题成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        QuestionFrame.main(null);
                    }
                }
            }
        });
        btnconfirm.setBounds(79, 432, 97, 23);
        contentPane.add(btnconfirm);

        JButton btnreset = new JButton("重置");
        btnreset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
            }
        });
        btnreset.setBounds(62, 26, 97, 23);
        contentPane.add(btnreset);

        JButton btnreturn = new JButton("返回");
        btnreturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                QuestionFrame.main(null);
            }
        });
        btnreturn.setBounds(211, 432, 97, 23);
        contentPane.add(btnreturn);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddQuestionFrame frame = new AddQuestionFrame();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void run(int id) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddQuestionFrame frame = new AddQuestionFrame(id);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
