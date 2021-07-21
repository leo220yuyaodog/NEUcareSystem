package com.careSystem.view;


import com.careSystem.pojo.Estimate;
import com.careSystem.pojo.Question;
import com.careSystem.service.impl.FQLinkServiceImpl;
import com.careSystem.utils.IconHandler;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class QuziFrame extends JFrame {

    private JPanel contentPane;
    private List<JRadioButton> radioButton1s = new ArrayList<>();
    private List<JRadioButton> radioButton2s = new ArrayList<>();
    private List<JRadioButton> radioButton3s = new ArrayList<>();

    /**
     * Create the frame.
     */
    public QuziFrame(int formId, Estimate estimate) {
        setSize(450, 600);
        setVisible(true);
        getContentPane().setLayout(null);
        setTitle("答题");
        ImageIcon frameIcon = new ImageIcon("./img/quiz.png");
        setIconImage(frameIcon.getImage());
        JPanel jPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(jPanel);
        scrollPane.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.CYAN, new Color(0, 255, 255), new Color(94, 203, 203), null));
        scrollPane.setSize(435, 565);
        getContentPane().add(scrollPane);

        List<Question> questions = FQLinkServiceImpl.getInstance().findQuestion(formId);
        System.out.println(questions);
        int row = questions.size();
        GridLayout layout = new GridLayout(row + 1, 1);
        layout.setVgap(20);
        jPanel.setLayout(layout);
        int cnt = 0;
        for (Question question : questions) {

            // 创建一个垂直箱容器
            Box vBox = Box.createVerticalBox();
            JLabel labelOrder = new JLabel("题目" + String.valueOf(++cnt));
            labelOrder.setHorizontalAlignment(SwingConstants.CENTER);//center label text
            Font font = new Font("仿宋", Font.PLAIN, 20);
            Font font2 = new Font("微软雅黑", Font.PLAIN, 16);
            labelOrder.setFont(font);

            labelOrder.setIcon(IconHandler.resizeIcon("./img/quiz2.png"));

            vBox.add(labelOrder);

            JLabel jLabel = new JLabel(question.getQuestion() + "?");
            jLabel.setFont(font2);
            vBox.add(jLabel);//加入问题

            JRadioButton radioButton1 = new JRadioButton(question.getAnswers().get(0));//设置按钮文本
            JRadioButton radioButton2 = new JRadioButton(question.getAnswers().get(1));
            JRadioButton radioButton3 = new JRadioButton(question.getAnswers().get(2));
            radioButton1.setFont(font2);//设置按钮字体
            radioButton2.setFont(font2);
            radioButton3.setFont(font2);
            radioButton1s.add(radioButton1);//加入按钮列表
            radioButton2s.add(radioButton2);
            radioButton3s.add(radioButton3);


            ButtonGroup btnGroup = new ButtonGroup();
            btnGroup.add(radioButton1);
            btnGroup.add(radioButton2);
            btnGroup.add(radioButton3);//把单选按钮添加到按钮组

            vBox.add(radioButton1);
            vBox.add(radioButton2);
            vBox.add(radioButton3);//单选按钮加入vbox
            jPanel.add(vBox);
        }
        JPanel btnPane = new JPanel();

        JButton submitBtn = new JButton("提交");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int score = 0;
                //遍历三个按钮组，计算得分
                for (JRadioButton r : radioButton1s) {
                    if (r.isSelected()) {
                        score += 5;
                    }
                }
                for (JRadioButton r : radioButton2s) {
                    if (r.isSelected()) {
                        score += 3;
                    }
                }
                for (JRadioButton r : radioButton3s) {
                    if (r.isSelected()) {
                        score += 1;
                    }
                }
                System.out.println(score);//测试，打印分数
                dispose();
                AdviceFrame.run(String.valueOf(score), estimate);//传入参数：分数和评估对象 打开建议界面
            }
        });

        btnPane.add(submitBtn);
        jPanel.add(btnPane);


    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuziFrame frame = new QuziFrame(9, new Estimate());
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void run(int id, Estimate estimate) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuziFrame frame = new QuziFrame(id, estimate);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
