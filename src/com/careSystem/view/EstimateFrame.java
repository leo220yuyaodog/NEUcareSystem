package com.careSystem.view;

import com.careSystem.pojo.Estimate;
import com.careSystem.pojo.Patient;
import com.careSystem.pojo.Worker;
import com.careSystem.service.FormService;
import com.careSystem.service.PatientService;
import com.careSystem.service.WorkService;
import com.careSystem.tables.EstimateTable;
import com.careSystem.utils.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class EstimateFrame extends JFrame {

    private static JTable table;
    private JPanel contentPane;
    private PatientService patientService = (PatientService) Util.getObject("patient.service");
    private FormService formService = (FormService) Util.getObject("form.service");
    private String formWorkName;

    private Estimate estimate = new Estimate();
    private WorkService workService = (WorkService) Util.getObject("work.service");

    /**
     * Create the frame.
     *
     * @param patientId
     */
    public EstimateFrame(String patientId, String username) {

        Worker worker = workService.findByUsername(username);
        if (worker == null)
            worker = new Worker();
        Patient patient = patientService.findById(patientId);

        ImageIcon frameIcon = new ImageIcon("./img/estimate.png");
        setIconImage(frameIcon.getImage());
        setTitle("评估界面");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 742, 460);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(formService.getList()));
        comboBox.setBounds(567, 63, 89, 23);
        comboBox.setSelectedIndex(0);
        formWorkName = (String) comboBox.getSelectedItem();
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    formWorkName = (String) comboBox.getSelectedItem();
                    System.out.println(formWorkName);//测试
                }
            }
        });
        contentPane.add(comboBox);

        JButton btnstart = new JButton("开始评估");
        Worker finalWorker = worker;
        btnstart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estimate.setEstimator(finalWorker.getName());//设置评估人姓名
                estimate.setName(patient.getName());
                estimate.setFormWorkName(formWorkName);
                estimate.setFormWorkType(formService.getFormWork(formWorkName).getType());
                estimate.setSex(patient.getSex());
                SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                estimate.setDate(myFmt.format(new Date()));
                QuziFrame.run(formService.getFormWork(formWorkName).getId(), estimate);
                System.out.println(formService.getFormWork(formWorkName));
            }
        });
        btnstart.setBounds(52, 63, 97, 23);
        contentPane.add(btnstart);

        table = new JTable(new EstimateTable());
        table.getColumnModel().getColumn(1).setCellRenderer(new EstimateRender());
        table.getColumnModel().getColumn(3).setCellRenderer(new EstimateRender());

        fitTableColumns(table);
        table.setBounds(53, 120, 622, 243);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        contentPane.add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(52, 120, 623, 243);
        contentPane.add(scrollPane);

        JButton btnreturn = new JButton("返回");
        btnreturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnreturn.setBounds(461, 390, 97, 23);
        contentPane.add(btnreturn);

        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setText("病人 :" + patient.getName());
        ImageIcon frameIcon3 = new ImageIcon("./img/patient.png");
        lblNewLabel.setIcon(new ImageIcon(frameIcon3.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        lblNewLabel.setBounds(188, 57, 103, 35);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setText("评估人:" + worker.getName());
        ImageIcon frameIcon2 = new ImageIcon("./img/doctor.png");
        lblNewLabel_1.setIcon(new ImageIcon(frameIcon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        lblNewLabel_1.setBounds(188, 10, 121, 37);
        contentPane.add(lblNewLabel_1);
    }

    public static void refreshTable() {
        table.setModel(new EstimateTable());
        fitTableColumns(table);
    }


    /**
     * Launch the application.
     */
    public static void run(String id, String uname) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EstimateFrame frame = new EstimateFrame(id, uname);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void fitTableColumns(JTable myTable) {//根据表格文本自动调整列宽
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();//得到行数

        Enumeration columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) myTable.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(myTable, column.getIdentifier()
                            , false, false, -1, col).getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
                        myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);
            column.setWidth(width + myTable.getIntercellSpacing().width);
        }
    }
}

/**
 * 表格渲染，设置文本居中显示
 */
class EstimateRender extends DefaultTableCellRenderer {
    public EstimateRender() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
