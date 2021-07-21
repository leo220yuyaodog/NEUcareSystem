package com.careSystem.view;

import com.careSystem.pojo.Estimate;
import com.careSystem.service.impl.EstimateServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdviceFrame extends JFrame {

    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public AdviceFrame(String score, Estimate estimate) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 575, 380);
        setTitle("建议");
        ImageIcon frameIcon = new ImageIcon("./img/advice.png");
        setIconImage(frameIcon.getImage());
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(56, 80, 446, 215);
        contentPane.add(textArea);

        JLabel lblNewtip = new JLabel("建议");
        lblNewtip.setHorizontalAlignment(SwingConstants.CENTER);//center label text
        Font font = new Font("仿宋", Font.PLAIN, 20);
        lblNewtip.setFont(font);

        lblNewtip.setBounds(56, 55, 58, 26);
        contentPane.add(lblNewtip);

        JLabel lblNewLabel = new JLabel("得分");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);//center label text
        lblNewLabel.setFont(font);
        lblNewLabel.setBounds(397, 16, 58, 20);
        contentPane.add(lblNewLabel);

        JLabel lblscore = new JLabel("");
        lblscore.setText(score);
        lblscore.setFont(font);
        lblscore.setBounds(444, 16, 58, 15);
        contentPane.add(lblscore);

        JButton btnNewButton = new JButton("保存");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String score = "测试得分" + lblscore.getText() + " | ";//保存形式 分数
                String advice = score + textArea.getText();//保存形式  +建议
                estimate.setAdvice(advice);
                EstimateServiceImpl.getInstance().add(estimate);//添加评估记录
                EstimateFrame.refreshTable();//刷新评估界面表格
                dispose();
            }
        });
        btnNewButton.setBounds(225, 305, 97, 23);
        contentPane.add(btnNewButton);
    }

    /**
     * Launch the application.
     */
    public static void run(String score, Estimate estimate) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdviceFrame frame = new AdviceFrame(score, estimate);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
