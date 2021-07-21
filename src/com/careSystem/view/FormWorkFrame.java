package com.careSystem.view;

import com.careSystem.pojo.FQ_Link;
import com.careSystem.pojo.FormWork;
import com.careSystem.service.FormService;
import com.careSystem.service.impl.FQLinkServiceImpl;
import com.careSystem.tables.FQLinkTable;
import com.careSystem.utils.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FormWorkFrame extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private FormService formService = (FormService) Util.getObject("form.service");

    /**
     * Create the frame.
     */
    public FormWorkFrame(Integer id) {
        setTitle("模板");
        ImageIcon frameIcon = new ImageIcon("./img/formwork.png");
        setIconImage(frameIcon.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 586, 407);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        table = new JTable();
        table.setModel(new FQLinkTable(id));
        table.getColumnModel().getColumn(1).setCellRenderer(new FrameRender());
        table.getColumnModel().getColumn(3).setCellRenderer(new FrameRender());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(43, 76, 488, 240);
        contentPane.add(scrollPane);

        JButton btnadd = new JButton("添加");
        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                QuestionFrame.run(id);
            }
        });
        btnadd.setBounds(408, 36, 97, 23);
        contentPane.add(btnadd);

        JButton btnreturn = new JButton("返回");
        btnreturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                FormWorkList.main(null);
            }
        });
        btnreturn.setBounds(332, 337, 97, 23);
        contentPane.add(btnreturn);

        JButton btndelete = new JButton("删除");
        btndelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> questionIds = new ArrayList<>();
                List<FQ_Link> fq_links = new ArrayList<>();

                for (int i = 0; i < table.getRowCount(); i++) {
                    System.out.println(table.getValueAt(i, 0));
                    if (((Boolean) table.getValueAt(i, 0))) {
                        questionIds.add((Integer) table.getValueAt(i, 1));
                    }
                }
                for (Integer qId : questionIds) {
                    fq_links.add(new FQ_Link(id, qId));
                }
                Boolean re = FQLinkServiceImpl.getInstance().delete(fq_links);
                if (re) {
                    table.setModel(new FQLinkTable(id));
                }
            }
        });
        btndelete.setBounds(116, 337, 97, 23);
        contentPane.add(btndelete);

        JLabel lblname = new JLabel("");
        lblname.setText(formService.getFormWork(id).getName());
        lblname.setBounds(78, 40, 58, 15);
        contentPane.add(lblname);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(43, 76, 488, 240);
        contentPane.add(tabbedPane);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormWorkFrame frame = new FormWorkFrame(0);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void run(Integer id) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormWorkFrame frame = new FormWorkFrame(id);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

/**
 * 表格渲染，设置文本居中显示
 */
class FrameRender extends DefaultTableCellRenderer {
    public FrameRender() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
