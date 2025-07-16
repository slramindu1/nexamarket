/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package dev.ramindu.nexamarket.admin.panel;

/**
 *
 * @author Ramindu
 */
import dev.ramindu.nexamarket.components.TimelineChartPanel;
import dev.ramindu.nexamarket.components.chart.ModelChart;
import dev.ramindu.nexamarket.utils.NumberAnimator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedHashMap;
import java.util.Map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class DashboardPanel extends javax.swing.JPanel {

    /**
     * Creates new form DashboardPanel
     */
    public DashboardPanel() {
        initComponents(); // GUI Builder-initialized components
        loadDashboardData();
        // Set circular progress value after initialization
        circularProgressBar.setProgress(90);  // ✅ Only once, after init

        // ➤ Supermarket Product Stock Data (example percentages)
        stackedProgressBar1.clearSections();
        stackedProgressBar1.addSection("Rice", new Color(255, 99, 132), 15);        // Pink-red
        stackedProgressBar1.addSection("Dhal", new Color(255, 159, 64), 10);        // Orange
        stackedProgressBar1.addSection("Sugar", new Color(255, 205, 86), 10);       // Yellow
        stackedProgressBar1.addSection("Milk", new Color(75, 192, 192), 10);        // Teal
        stackedProgressBar1.addSection("Fruits", new Color(54, 162, 235), 20);      // Blue
        stackedProgressBar1.addSection("Vegetables", new Color(153, 102, 255), 25); // Purple
        stackedProgressBar1.addSection("Other", new Color(201, 203, 207), 10);      // Grey

        lineChart.addLegend("Income", new Color(12, 84, 175), new Color(0, 108, 247));
        lineChart.addLegend("Expense", new Color(54, 4, 143), new Color(104, 49, 200));
        lineChart.addLegend("Profit", new Color(5, 125, 0), new Color(95, 209, 69));
        lineChart.addLegend("Cost", new Color(186, 37, 37), new Color(241, 100, 120));
        lineChart.addData(new ModelChart("January", new double[]{500, 200, 80, 89}));
        lineChart.addData(new ModelChart("February", new double[]{600, 750, 90, 150}));
        lineChart.addData(new ModelChart("March", new double[]{200, 350, 460, 900}));
        lineChart.addData(new ModelChart("April", new double[]{480, 150, 750, 700}));
        lineChart.addData(new ModelChart("May", new double[]{350, 540, 300, 150}));
        lineChart.addData(new ModelChart("June", new double[]{190, 280, 81, 200}));
        lineChart.start();

        // ➤ Payment Method Pie Chart
        Map<String, Double> payments = new LinkedHashMap<>();
        payments.put("Cash", 50.0);
        payments.put("Card", 30.0);
        payments.put("Online", 20.0);
        pieChartPanel.setData(payments);

        // Set Header Style
        JTableHeader header = jTable1.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(0, 204, 102)); // light green
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

// Set Row Height and Grid
        jTable1.setRowHeight(32);
        jTable1.setShowGrid(false);
        jTable1.setIntercellSpacing(new Dimension(0, 0));

// Remove borders
        jTable1.setBorder(null);
        ((JScrollPane) jTable1.getParent().getParent()).setBorder(null);

// Font for cells
        jTable1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jTable1.setForeground(Color.BLACK);

// Set cell renderer for alternating row colors
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(245, 245, 245)); // light gray
                    }
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(new Color(0, 120, 215));
                    c.setForeground(Color.WHITE);
                }

                setHorizontalAlignment(CENTER); // Center align all cells
                return c;
            }
        };

// Apply to all columns
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    private void loadDashboardData() {
        String sqlSales = "SELECT SUM(total_price) FROM sales WHERE YEARWEEK(DATE(sale_date), 1) = YEARWEEK(CURDATE(), 1)";
        String sqlOrders = "SELECT COUNT(*) FROM sales WHERE YEARWEEK(DATE(sale_date), 1) = YEARWEEK(CURDATE(), 1)";
        String sqlProfit = "SELECT SUM(profit) FROM sales WHERE YEARWEEK(DATE(sale_date), 1) = YEARWEEK(CURDATE(), 1)";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nexamarket", "root", "1234")) {

            // Sales
            try (PreparedStatement ps = con.prepareStatement(sqlSales); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lblWeeklySales.setText("Rs. " + rs.getDouble(1));
                }
            }

            // Orders
            try (PreparedStatement ps = con.prepareStatement(sqlOrders); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lblWeeklyOrders.setText(String.valueOf(rs.getInt(1)));
                }
            }

            // Profit
            try (PreparedStatement ps = con.prepareStatement(sqlProfit); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lblWeeklyProfit.setText("Rs. " + rs.getDouble(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        roundedPanel7 = new dev.ramindu.nexamarket.components.RoundedPanel();
        gradientCardPanel21 = new dev.ramindu.nexamarket.components.GradientCardPanel2();
        jLabel4 = new javax.swing.JLabel();
        lblWeeklySales = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        roundedPanel9 = new dev.ramindu.nexamarket.components.RoundedPanel();
        jLabel2 = new javax.swing.JLabel();
        roundedPanel10 = new dev.ramindu.nexamarket.components.RoundedPanel();
        jLabel3 = new javax.swing.JLabel();
        roundedPanel4 = new dev.ramindu.nexamarket.components.RoundedPanel();
        pieChartPanel = new dev.ramindu.nexamarket.components.PieChartPanel();
        jLabel16 = new javax.swing.JLabel();
        roundedPanel8 = new dev.ramindu.nexamarket.components.RoundedPanel();
        gradientCardPanel11 = new dev.ramindu.nexamarket.components.GradientCardPanel1();
        jLabel9 = new javax.swing.JLabel();
        lblWeeklyOrders = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        roundedPanel11 = new dev.ramindu.nexamarket.components.RoundedPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        roundButton1 = new dev.ramindu.nexamarket.components.RoundButton();
        circularProgressBar = new dev.ramindu.nexamarket.components.CircularProgressBar();
        roundedPanel13 = new dev.ramindu.nexamarket.components.RoundedPanel();
        gradientCardPanel1 = new dev.ramindu.nexamarket.components.GradientCardPanel();
        jLabel14 = new javax.swing.JLabel();
        lblWeeklyProfit = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        roundedPanel18 = new dev.ramindu.nexamarket.components.RoundedPanel();
        jLabel15 = new javax.swing.JLabel();
        stackedProgressBar1 = new dev.ramindu.nexamarket.components.StackedProgressBar();
        roundedPanel12 = new dev.ramindu.nexamarket.components.RoundedPanel();
        lineChart = new dev.ramindu.nexamarket.components.chart.LineChart();
        roundedPanel15 = new dev.ramindu.nexamarket.components.RoundedPanel();
        polarAreaChartPanel2 = new dev.ramindu.nexamarket.components.PolarAreaChartPanel();
        calendar2 = new dev.ramindu.nexamarket.calander.Calendar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        roundedPanel1 = new dev.ramindu.nexamarket.components.RoundedPanel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/home.png"))); // NOI18N
        jLabel1.setText("  Dashboard");

        roundedPanel7.setBackground(new java.awt.Color(255, 255, 255));

        gradientCardPanel21.setPreferredSize(new java.awt.Dimension(455, 272));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Weekly Sales");

        lblWeeklySales.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblWeeklySales.setForeground(new java.awt.Color(255, 255, 255));
        lblWeeklySales.setText("Rs. 150,000");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Increased By 20%");

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/chart.png"))); // NOI18N

        javax.swing.GroupLayout gradientCardPanel21Layout = new javax.swing.GroupLayout(gradientCardPanel21);
        gradientCardPanel21.setLayout(gradientCardPanel21Layout);
        gradientCardPanel21Layout.setHorizontalGroup(
            gradientCardPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientCardPanel21Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(gradientCardPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gradientCardPanel21Layout.createSequentialGroup()
                        .addGroup(gradientCardPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(lblWeeklySales))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(gradientCardPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22)
                        .addGap(44, 44, 44))))
        );
        gradientCardPanel21Layout.setVerticalGroup(
            gradientCardPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientCardPanel21Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(gradientCardPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel22))
                .addGap(44, 44, 44)
                .addComponent(lblWeeklySales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout roundedPanel7Layout = new javax.swing.GroupLayout(roundedPanel7);
        roundedPanel7.setLayout(roundedPanel7Layout);
        roundedPanel7Layout.setHorizontalGroup(
            roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientCardPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );
        roundedPanel7Layout.setVerticalGroup(
            roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientCardPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
        );

        roundedPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/online.png"))); // NOI18N
        jLabel2.setText("  Present 2 Cashiers");

        javax.swing.GroupLayout roundedPanel9Layout = new javax.swing.GroupLayout(roundedPanel9);
        roundedPanel9.setLayout(roundedPanel9Layout);
        roundedPanel9Layout.setHorizontalGroup(
            roundedPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel9Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundedPanel9Layout.setVerticalGroup(
            roundedPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundedPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/offline.png"))); // NOI18N
        jLabel3.setText("  Absent 2 Cashiers");

        javax.swing.GroupLayout roundedPanel10Layout = new javax.swing.GroupLayout(roundedPanel10);
        roundedPanel10.setLayout(roundedPanel10Layout);
        roundedPanel10Layout.setHorizontalGroup(
            roundedPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel10Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundedPanel10Layout.setVerticalGroup(
            roundedPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundedPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pieChartPanelLayout = new javax.swing.GroupLayout(pieChartPanel);
        pieChartPanel.setLayout(pieChartPanelLayout);
        pieChartPanelLayout.setHorizontalGroup(
            pieChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );
        pieChartPanelLayout.setVerticalGroup(
            pieChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Payment Methods");

        javax.swing.GroupLayout roundedPanel4Layout = new javax.swing.GroupLayout(roundedPanel4);
        roundedPanel4.setLayout(roundedPanel4Layout);
        roundedPanel4Layout.setHorizontalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel4Layout.createSequentialGroup()
                        .addComponent(pieChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(126, 126, 126))))
        );
        roundedPanel4Layout.setVerticalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel4Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pieChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        roundedPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Weekly Orders");

        lblWeeklyOrders.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblWeeklyOrders.setForeground(new java.awt.Color(255, 255, 255));
        lblWeeklyOrders.setText("Rs. 250,000");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Increased By 60%");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/bookmark.png"))); // NOI18N

        javax.swing.GroupLayout gradientCardPanel11Layout = new javax.swing.GroupLayout(gradientCardPanel11);
        gradientCardPanel11.setLayout(gradientCardPanel11Layout);
        gradientCardPanel11Layout.setHorizontalGroup(
            gradientCardPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientCardPanel11Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(gradientCardPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gradientCardPanel11Layout.createSequentialGroup()
                        .addGroup(gradientCardPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(lblWeeklyOrders))
                        .addContainerGap(202, Short.MAX_VALUE))
                    .addGroup(gradientCardPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addGap(34, 34, 34))))
        );
        gradientCardPanel11Layout.setVerticalGroup(
            gradientCardPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientCardPanel11Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(gradientCardPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel23))
                .addGap(40, 40, 40)
                .addComponent(lblWeeklyOrders)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundedPanel8Layout = new javax.swing.GroupLayout(roundedPanel8);
        roundedPanel8.setLayout(roundedPanel8Layout);
        roundedPanel8Layout.setHorizontalGroup(
            roundedPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientCardPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        roundedPanel8Layout.setVerticalGroup(
            roundedPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel8Layout.createSequentialGroup()
                .addComponent(gradientCardPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        roundedPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel10.setText("Your Attendace");

        jLabel11.setText("View Your Monthly Attendace Percentage Very Easily");

        roundButton1.setText("Mark Attendance");
        roundButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout circularProgressBarLayout = new javax.swing.GroupLayout(circularProgressBar);
        circularProgressBar.setLayout(circularProgressBarLayout);
        circularProgressBarLayout.setHorizontalGroup(
            circularProgressBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        circularProgressBarLayout.setVerticalGroup(
            circularProgressBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundedPanel11Layout = new javax.swing.GroupLayout(roundedPanel11);
        roundedPanel11.setLayout(roundedPanel11Layout);
        roundedPanel11Layout.setHorizontalGroup(
            roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel11Layout.createSequentialGroup()
                .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundedPanel11Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(circularProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                    .addGroup(roundedPanel11Layout.createSequentialGroup()
                        .addContainerGap(53, Short.MAX_VALUE)
                        .addComponent(roundButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
            .addGroup(roundedPanel11Layout.createSequentialGroup()
                .addGroup(roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel11Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel10))
                    .addGroup(roundedPanel11Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel11Layout.setVerticalGroup(
            roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel11Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(circularProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        roundedPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Updated Profit");

        lblWeeklyProfit.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblWeeklyProfit.setForeground(new java.awt.Color(255, 255, 255));
        lblWeeklyProfit.setText("Rs. 45,000");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Increased By 40%");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/profit.png"))); // NOI18N
        jLabel24.setToolTipText("");

        javax.swing.GroupLayout gradientCardPanel1Layout = new javax.swing.GroupLayout(gradientCardPanel1);
        gradientCardPanel1.setLayout(gradientCardPanel1Layout);
        gradientCardPanel1Layout.setHorizontalGroup(
            gradientCardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientCardPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(gradientCardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gradientCardPanel1Layout.createSequentialGroup()
                        .addGroup(gradientCardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(lblWeeklyProfit))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(gradientCardPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24)
                        .addGap(33, 33, 33))))
        );
        gradientCardPanel1Layout.setVerticalGroup(
            gradientCardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientCardPanel1Layout.createSequentialGroup()
                .addGroup(gradientCardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gradientCardPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel14))
                    .addGroup(gradientCardPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel24)))
                .addGap(53, 53, 53)
                .addComponent(lblWeeklyProfit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundedPanel13Layout = new javax.swing.GroupLayout(roundedPanel13);
        roundedPanel13.setLayout(roundedPanel13Layout);
        roundedPanel13Layout.setHorizontalGroup(
            roundedPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientCardPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        roundedPanel13Layout.setVerticalGroup(
            roundedPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientCardPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        roundedPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Upcoming Stock");

        javax.swing.GroupLayout stackedProgressBar1Layout = new javax.swing.GroupLayout(stackedProgressBar1);
        stackedProgressBar1.setLayout(stackedProgressBar1Layout);
        stackedProgressBar1Layout.setHorizontalGroup(
            stackedProgressBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        stackedProgressBar1Layout.setVerticalGroup(
            stackedProgressBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundedPanel18Layout = new javax.swing.GroupLayout(roundedPanel18);
        roundedPanel18.setLayout(roundedPanel18Layout);
        roundedPanel18Layout.setHorizontalGroup(
            roundedPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stackedProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(roundedPanel18Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel15)
                .addContainerGap(322, Short.MAX_VALUE))
        );
        roundedPanel18Layout.setVerticalGroup(
            roundedPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel18Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stackedProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        roundedPanel12.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout roundedPanel12Layout = new javax.swing.GroupLayout(roundedPanel12);
        roundedPanel12.setLayout(roundedPanel12Layout);
        roundedPanel12Layout.setHorizontalGroup(
            roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lineChart, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel12Layout.setVerticalGroup(
            roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        roundedPanel15.setBackground(new java.awt.Color(255, 255, 255));

        polarAreaChartPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout polarAreaChartPanel2Layout = new javax.swing.GroupLayout(polarAreaChartPanel2);
        polarAreaChartPanel2.setLayout(polarAreaChartPanel2Layout);
        polarAreaChartPanel2Layout.setHorizontalGroup(
            polarAreaChartPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        polarAreaChartPanel2Layout.setVerticalGroup(
            polarAreaChartPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundedPanel15Layout = new javax.swing.GroupLayout(roundedPanel15);
        roundedPanel15.setLayout(roundedPanel15Layout);
        roundedPanel15Layout.setHorizontalGroup(
            roundedPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(polarAreaChartPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel15Layout.setVerticalGroup(
            roundedPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(polarAreaChartPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Cashier Name", "Cashier Email", "Payment Date", "Paid Amount"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("Monthly Cashier Payment");

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(roundedPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundedPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundedPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundedPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(roundedPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(roundedPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(35, 35, 35))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(roundedPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel25)
                                        .addGap(322, 322, 322))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(35, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(calendar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(roundedPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(roundedPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(roundedPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(35, 35, 35))))))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(roundedPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(roundedPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(roundedPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(calendar2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(roundedPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundedPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundedPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundedPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(roundedPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(roundedPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(roundedPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private dev.ramindu.nexamarket.calander.Calendar calendar2;
    private dev.ramindu.nexamarket.components.CircularProgressBar circularProgressBar;
    private dev.ramindu.nexamarket.components.GradientCardPanel gradientCardPanel1;
    private dev.ramindu.nexamarket.components.GradientCardPanel1 gradientCardPanel11;
    private dev.ramindu.nexamarket.components.GradientCardPanel2 gradientCardPanel21;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblWeeklyOrders;
    private javax.swing.JLabel lblWeeklyProfit;
    private javax.swing.JLabel lblWeeklySales;
    private dev.ramindu.nexamarket.components.chart.LineChart lineChart;
    private dev.ramindu.nexamarket.components.PieChartPanel pieChartPanel;
    private dev.ramindu.nexamarket.components.PolarAreaChartPanel polarAreaChartPanel2;
    private dev.ramindu.nexamarket.components.RoundButton roundButton1;
    private dev.ramindu.nexamarket.components.RoundedPanel roundedPanel1;
    private dev.ramindu.nexamarket.components.RoundedPanel roundedPanel10;
    private dev.ramindu.nexamarket.components.RoundedPanel roundedPanel11;
    private dev.ramindu.nexamarket.components.RoundedPanel roundedPanel12;
    private dev.ramindu.nexamarket.components.RoundedPanel roundedPanel13;
    private dev.ramindu.nexamarket.components.RoundedPanel roundedPanel15;
    private dev.ramindu.nexamarket.components.RoundedPanel roundedPanel18;
    private dev.ramindu.nexamarket.components.RoundedPanel roundedPanel4;
    private dev.ramindu.nexamarket.components.RoundedPanel roundedPanel7;
    private dev.ramindu.nexamarket.components.RoundedPanel roundedPanel8;
    private dev.ramindu.nexamarket.components.RoundedPanel roundedPanel9;
    private dev.ramindu.nexamarket.components.StackedProgressBar stackedProgressBar1;
    // End of variables declaration//GEN-END:variables
}
