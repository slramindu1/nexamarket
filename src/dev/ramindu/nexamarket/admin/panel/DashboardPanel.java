/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package dev.ramindu.nexamarket.admin.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Ramindu
 */
public class DashboardPanel extends javax.swing.JPanel {

    /**
     * Creates new form DashboardPanel
     */
    public DashboardPanel() {
        initComponents(); 
        loadTransactionDetails();
        loadBestSellingProducts();
        loadWeeklyEarnings();
        loadWeeklyPercentageIncrease();
        loadWeeklyStats();
        loadWeeklySalesCount();
        loadMonthlyIncome();

    
        JTableHeader header = jTable1.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setOpaque(true);
        header.setBackground(new Color(242, 242, 242));
        header.setForeground(new Color(51, 51, 51));
        header.setPreferredSize(new Dimension(header.getWidth(), 38));


        jTable1.setRowHeight(34);
        jTable1.setShowGrid(false);
        jTable1.setIntercellSpacing(new Dimension(0, 0));
        jTable1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jTable1.setForeground(new Color(51, 51, 51));
        jTable1.setSelectionBackground(new Color(255, 228, 181));
        jTable1.setSelectionForeground(Color.BLACK);


        jTable1.setBorder(null);
        JScrollPane scrollPane = (JScrollPane) jTable1.getParent().getParent();
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.WHITE);

        int statusColumnIndex = -1;
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            if (jTable1.getColumnName(i).equalsIgnoreCase("Status")) {
                statusColumnIndex = i;
                break;
            }
        }


        DefaultTableCellRenderer defaultRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(250, 250, 250));
                    c.setForeground(new Color(51, 51, 51));
                }
                setHorizontalAlignment(CENTER);
                return c;
            }
        };

        DefaultTableCellRenderer statusRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                String status = value.toString();
                if (status.equals("Completed")) {
                    c.setForeground(new Color(0, 153, 0)); 
                } else if (status.equals("Pending")) {
                    c.setForeground(new Color(255, 140, 0));
                } else if (status.equals("Cancelled")) {
                    c.setForeground(Color.RED); 
                } else {
                    c.setForeground(new Color(51, 51, 51));
                }

                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(250, 250, 250));
                }

                setHorizontalAlignment(CENTER);
                return c;
            }
        };

        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            if (i == statusColumnIndex) {
                jTable1.getColumnModel().getColumn(i).setCellRenderer(statusRenderer);
            } else {
                jTable1.getColumnModel().getColumn(i).setCellRenderer(defaultRenderer);
            }
        }

        JTableHeader header1 = jTable2.getTableHeader();
        header1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header1.setOpaque(true);
        header1.setBackground(new Color(242, 242, 242)); 
        header1.setForeground(new Color(51, 51, 51)); 
        header1.setPreferredSize(new Dimension(header1.getWidth(), 38));


        jTable2.setRowHeight(34);
        jTable2.setShowGrid(false);
        jTable2.setIntercellSpacing(new Dimension(0, 0));
        jTable2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jTable2.setForeground(new Color(51, 51, 51)); 
        jTable2.setSelectionBackground(new Color(255, 228, 181)); 
        jTable2.setSelectionForeground(Color.BLACK);

        jTable2.setBorder(null);
        JScrollPane scrollPane1 = (JScrollPane) jTable1.getParent().getParent();
        scrollPane1.setBorder(null);
        scrollPane1.getViewport().setBackground(Color.WHITE);

        DefaultTableCellRenderer cellRenderer1 = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(250, 250, 250));
                    c.setForeground(new Color(51, 51, 51));
                }
                setHorizontalAlignment(CENTER); 
                return c;
            }
        };

        for (int i = 0; i < jTable2.getColumnCount(); i++) {
            jTable2.getColumnModel().getColumn(i).setCellRenderer(cellRenderer1);
        }

    }

    private void loadBestSellingProducts() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nexamarket", "root", "1234");

            String sql = """
            SELECT 
                p.name AS product_name,
                SUM(s.quantity) AS total_quantity_sold,
                p.price AS unit_price,
                SUM(s.total_price) AS total_selling_price
            FROM sales s
            JOIN product p ON s.product_id = p.id
            GROUP BY p.id, p.name, p.price
            ORDER BY total_quantity_sold DESC
        """;

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            // Get table model & clear rows
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                String productName = rs.getString("product_name");
                int quantitySold = rs.getInt("total_quantity_sold");
                double unitPrice = rs.getDouble("unit_price");
                double totalSellingPrice = rs.getDouble("total_selling_price");

                model.addRow(new Object[]{productName, quantitySold, unitPrice, totalSellingPrice});
            }

            rs.close();
            statement.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMonthlyIncome() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nexamarket", "root", "1234");

            String sql = """
            SELECT
                (SELECT IFNULL(SUM(total_price), 0) 
                 FROM sales 
                 WHERE YEAR(sale_date) = YEAR(CURDATE()) 
                   AND MONTH(sale_date) = MONTH(CURDATE())) AS this_month,
                (SELECT IFNULL(SUM(total_price), 0) 
                 FROM sales 
                 WHERE YEAR(sale_date) = YEAR(CURDATE() - INTERVAL 1 MONTH) 
                   AND MONTH(sale_date) = MONTH(CURDATE() - INTERVAL 1 MONTH)) AS last_month
        """;

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                double thisMonth = rs.getDouble("this_month");
                double lastMonth = rs.getDouble("last_month");

                jLabel17.setText(String.format("%.2f", thisMonth));

        
                double percentage = 0;
                if (lastMonth > 0) {
                    percentage = ((thisMonth - lastMonth) / lastMonth) * 100;
                } else if (thisMonth > 0) {
                    percentage = 100;
                }

            
                String signArrow = percentage >= 0 ? "^ " : "v ";
                jLabel18.setText(signArrow + String.format("%.0f", Math.abs(percentage)) + "%");

            
                if (percentage > 0) {
                    jLabel19.setText("Increase compared to last month");
                } else if (percentage < 0) {
                    jLabel19.setText("Decrease compared to last month");
                } else {
                    jLabel19.setText("No change compared to last month");
                }
            }

            rs.close();
            statement.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            jLabel17.setText("Error");
            jLabel18.setText("Error");
            jLabel19.setText("Error");
        }
    }

    private void loadWeeklyStats() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nexamarket", "root", "1234");

            String sql = """
            SELECT
                (SELECT IFNULL(SUM(total_price), 0) 
                 FROM sales 
                 WHERE YEARWEEK(sale_date, 1) = YEARWEEK(CURDATE(), 1)) AS this_week,
                (SELECT IFNULL(SUM(total_price), 0) 
                 FROM sales 
                 WHERE YEARWEEK(sale_date, 1) = YEARWEEK(CURDATE(), 1) - 1) AS last_week
        """;

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                double thisWeek = rs.getDouble("this_week");
                double lastWeek = rs.getDouble("last_week");

              
                jLabel6.setText(String.format("%.2f", thisWeek));

                double percentage = 0;
                if (lastWeek > 0) {
                    percentage = ((thisWeek - lastWeek) / lastWeek) * 100;
                } else if (thisWeek > 0) {
                    percentage = 100;
                }

             
                String signArrow = percentage >= 0 ? "^ " : "v ";
                jLabel7.setText(signArrow + String.format("%.0f", Math.abs(percentage)) + "%");

               
                if (percentage > 0) {
                    jLabel8.setText("Increase compared to last week");
                } else if (percentage < 0) {
                    jLabel8.setText("Decrease compared to last week");
                } else {
                    jLabel8.setText("No change compared to last week");
                }
            }

            rs.close();
            statement.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            jLabel6.setText("Error");
            jLabel7.setText("Error");
            jLabel8.setText("Error");
        }
    }

    private void loadWeeklySalesCount() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nexamarket", "root", "1234");

            String sql = """
            SELECT
                (SELECT COUNT(*) 
                 FROM sales 
                 WHERE YEARWEEK(sale_date, 1) = YEARWEEK(CURDATE(), 1)) AS this_week_count,
                (SELECT COUNT(*) 
                 FROM sales 
                 WHERE YEARWEEK(sale_date, 1) = YEARWEEK(CURDATE(), 1) - 1) AS last_week_count
        """;

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                int thisWeekCount = rs.getInt("this_week_count");
                int lastWeekCount = rs.getInt("last_week_count");

               
                jLabel12.setText(String.valueOf(thisWeekCount));

           
                double percentage = 0;
                if (lastWeekCount > 0) {
                    percentage = ((double) (thisWeekCount - lastWeekCount) / lastWeekCount) * 100;
                } else if (thisWeekCount > 0) {
                    percentage = 100;
                }

       
                String signArrow = percentage >= 0 ? "^ " : "v ";
                jLabel14.setText(signArrow + String.format("%.0f", Math.abs(percentage)) + "%");

              
                if (percentage > 0) {
                    jLabel13.setText("Increase compared to last week");
                } else if (percentage < 0) {
                    jLabel13.setText("Decrease compared to last week");
                } else {
                    jLabel13.setText("No change compared to last week");
                }
            }

            rs.close();
            statement.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            jLabel12.setText("Error");
            jLabel14.setText("Error");
            jLabel13.setText("Error");
        }
    }

    private void loadWeeklyPercentageIncrease() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nexamarket", "root", "1234");

            String sql = """
            SELECT
                (SELECT IFNULL(SUM(total_price), 0) 
                 FROM sales 
                 WHERE YEARWEEK(sale_date, 1) = YEARWEEK(CURDATE(), 1)) AS this_week,
                (SELECT IFNULL(SUM(total_price), 0) 
                 FROM sales 
                 WHERE YEARWEEK(sale_date, 1) = YEARWEEK(CURDATE(), 1) - 1) AS last_week
        """;

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                double thisWeek = rs.getDouble("this_week");
                double lastWeek = rs.getDouble("last_week");

                double percentage = 0;
                if (lastWeek > 0) {
                    percentage = ((thisWeek - lastWeek) / lastWeek) * 100;
                } else if (thisWeek > 0) {
                    
                    percentage = 100;
                }

              
                String sign = percentage >= 0 ? "^ " : "v ";
                jLabel7.setText(sign + String.format("%.0f", Math.abs(percentage)) + "%");
            }

            rs.close();
            statement.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            jLabel7.setText("Error");
        }
    }

    private void loadWeeklyEarnings() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nexamarket", "root", "1234");

            String sql = """
            SELECT IFNULL(SUM(total_price), 0) AS weekly_total
            FROM sales
            WHERE YEARWEEK(sale_date, 1) = YEARWEEK(CURDATE(), 1)
        """;

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                double weeklyTotal = rs.getDouble("weekly_total");
                jLabel6.setText(String.format("%.2f", weeklyTotal)); // 2 decimal format
            }

            rs.close();
            statement.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            jLabel6.setText("Error");
        }
    }

    private void loadTransactionDetails() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nexamarket",
                    "root",
                    "1234"
            );

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM transaction_details");

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"#", "Total Amount", "Total Qty", "Date & Time", "Status", "Payment Method"}, 0
            );

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("Total Amount"),
                    rs.getString("Total Qty"),
                    rs.getString("Date&Time"),
                    rs.getString("Status"),
                    rs.getString("Payment_Method")
                });
            }

            jTable1.setModel(model);

         
            int statusColumnIndex = -1;
            for (int i = 0; i < jTable1.getColumnCount(); i++) {
                if (jTable1.getColumnName(i).equalsIgnoreCase("Status")) {
                    statusColumnIndex = i;
                    break;
                }
            }

            if (statusColumnIndex != -1) {
            
                jTable1.getColumnModel().getColumn(statusColumnIndex).setCellRenderer(new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) {
                        Component comp = super.getTableCellRendererComponent(table, value,
                                isSelected, hasFocus, row, column);

                        String status = value.toString();

                      
                        if (status.equals("Completed")) {
                            comp.setForeground(new Color(0, 153, 0)); 
                        } else if (status.equals("Pending")) {
                            comp.setForeground(new Color(255, 140, 0)); 
                        } else if (status.equals("Cancelled")) {
                            comp.setForeground(Color.RED); 
                        } else {
                            comp.setForeground(Color.BLACK);
                        }

                        
                        setHorizontalAlignment(CENTER);

                       
                        if (isSelected) {
                            comp.setBackground(table.getSelectionBackground());
                        } else {
                            comp.setBackground(row % 2 == 0 ? Color.WHITE : new Color(250, 250, 250));
                        }

                        return comp;
                    }
                });
            }

         
            jTable1.setRowHeight(25);
            jTable1.setShowGrid(false);
            jTable1.setIntercellSpacing(new Dimension(0, 0));
            jTable1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            jTable1.setForeground(new Color(51, 51, 51));
            jTable1.setSelectionBackground(new Color(255, 228, 181));
            jTable1.setSelectionForeground(Color.BLACK);
            jTable1.setBorder(null);

            rs.close();
            statement.close();
            c.close();

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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/home.png"))); // NOI18N
        jLabel1.setText("  Dashboard");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Hi  Ramindu Ravihansa,");

        jLabel3.setText("here's what's happening with your store today");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(13, 13, 13))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/analytics (1).png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Weekly Earning");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel6.setText("$95000.45");

        jLabel7.setText("^ 49% ");

        jLabel8.setText("Increase Compare To Last Week");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(244, 145, 64));

        jLabel13.setText("Increase Compare To Last Week");

        jLabel14.setText("^ 49% ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel12.setText("1500");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Weekly Sales Count");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/analytics2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(36, 63, 81));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Monthly Income");

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Increase Compare To Last Week");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("1500");

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("^ 49% ");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/analytics3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel20)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Best Selling Products");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Product Name", "Orders", "Price", "Total Price"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "#", "Total Amount", "Total Qty", "Date & Time", "Status", "Payment Method"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Recent Transactions");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 863, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(209, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
