/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package dev.ramindu.nexamarket.admin.panel;

import com.toedter.calendar.JDateChooser;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ramindu
 */
public class IssueInvoicePanel extends javax.swing.JPanel {

    private JDateChooser dateChooser;

    /**
     * Creates new form ManageCustomersPanel
     */
    public IssueInvoicePanel() {
        initComponents();
        loadPaymentMethods();
        addCalculationListeners();
        loadCategoriesToComboBox();

        txtTotal.setEditable(false);

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

        // Reference Number
        txtProductName.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                suggestProductNames();
            }

            public void removeUpdate(DocumentEvent e) {
                suggestProductNames();
            }

            public void changedUpdate(DocumentEvent e) {
                suggestProductNames();
            }
        });

        String[] columnNames = {
            "Item name", "Quantity", "Unit price", "Total amount",
            "Item description", "Item category"
        };

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        jTable1.setModel(tableModel);

    }

    private void generateInvoiceReport() {
        try {
            // Step 1: Table Model to pass to Jasper
            DefaultTableModel reportModel = new DefaultTableModel();
            reportModel.addColumn("ProductName");   // ✅ Corrected name
            reportModel.addColumn("Qty");
            reportModel.addColumn("UnitPrice");
            reportModel.addColumn("TotalAmount");

            // Copy data from jTable1 to report model
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                Object[] row = {
                    jTable1.getValueAt(i, 0), // Product_name
                    jTable1.getValueAt(i, 1), // Qty
                    jTable1.getValueAt(i, 2), // Unit_price
                    jTable1.getValueAt(i, 3) // Total_amount
                };
                reportModel.addRow(row);
            }

            // Step 2: Create Jasper data source
            JRTableModelDataSource dataSource = new JRTableModelDataSource(reportModel);

            // Step 3: Create parameters map
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("TotalQty", txtTotalQty.getText());
            parameters.put("TotalPrice", txtTotalPayment.getText());

            // Step 4: Load compiled .jasper report file
            InputStream reportStream = getClass().getResourceAsStream(
                    "/dev/ramindu/nexamarket/admin/reports/Invoice.jasper"
            );

            if (reportStream == null) {
                throw new FileNotFoundException("Invoice.jasper report file not found.");
            }

            // Step 5: Fill and generate report
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parameters, dataSource);

            // Step 6: Show the report
            JasperViewer.viewReport(jasperPrint, false);

            // Optional Debugging
            System.out.println("Row count: " + reportModel.getRowCount());
            for (int i = 0; i < reportModel.getRowCount(); i++) {
                System.out.println(
                        reportModel.getValueAt(i, 0) + " | "
                        + reportModel.getValueAt(i, 1) + " | "
                        + reportModel.getValueAt(i, 2) + " | "
                        + reportModel.getValueAt(i, 3)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating invoice: " + e.getMessage());
        }
    }

    private void loadProductPrice(String productName) {
        try {
            Connection con = DB.getConnection();
            String sql = "SELECT price FROM product WHERE name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, productName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String price = rs.getString("price");
                txtUnitPrice.setText(price); // 👉 Set unit price
            } else {
                txtUnitPrice.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPopupMenu popup = new JPopupMenu();

    private void suggestProductNames() {
        String text = txtProductName.getText().trim();
        if (text.isEmpty()) {
            popup.setVisible(false); // ❗ hide popup if field empty
            return;
        }

        try {
            Connection con = DB.getConnection(); // ඔබගේ DB connection method එක
            String sql = "SELECT name FROM product WHERE name LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, text + "%");

            ResultSet rs = ps.executeQuery();

            popup.setVisible(false);      // 🔁 hide previous
            popup.removeAll();            // 🔁 clear previous items

            boolean hasResults = false;

            while (rs.next()) {
                hasResults = true;
                String name = rs.getString("name");

                JMenuItem item = new JMenuItem(name);

                item.addActionListener(e -> {
                    txtProductName.setText(name);
                    popup.setVisible(false);
                    loadProductDescription(name);
                    loadProductCategory(name);
                    loadProductPrice(name);  // ✅ this line must exist!
                });

                popup.add(item);
            }

            if (hasResults) {
                popup.show(txtProductName, 0, txtProductName.getHeight());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadProductCategory(String productName) {
        try {
            Connection con = DB.getConnection();
            String sql = "SELECT c.name FROM product p INNER JOIN category c ON p.category_id = c.id WHERE p.name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, productName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String categoryName = rs.getString("name").trim();
                System.out.println("Loaded category: " + categoryName); // 🟢 Debug

                for (int i = 0; i < cmbItemCategory.getItemCount(); i++) {
                    String item = cmbItemCategory.getItemAt(i).toString().trim();
                    System.out.println("Comparing: " + item); // 🟢 Debug
                    if (item.equalsIgnoreCase(categoryName)) {
                        cmbItemCategory.setSelectedIndex(i);
                        System.out.println("Matched and set!");
                        break;
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadCategoriesToComboBox() {
        try {
            Connection con = DB.getConnection(); // ඔබේ connection method එක
            String sql = "SELECT name FROM category";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            cmbItemCategory.removeAllItems(); // Clear old items

            while (rs.next()) {
                String categoryName = rs.getString("name");
                cmbItemCategory.addItem(categoryName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadProductDescription(String name) {
        try {
            Connection con = DB.getConnection(); // ඔබේ DB connection method එක
            String sql = "SELECT description FROM product WHERE name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String description = rs.getString("description");
                txtItemDescription.setText(description); // 👉 set description to text area
            } else {
                txtItemDescription.setText(""); // description නැත්නම් clear කරන්න
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class DB {

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/nexamarket", "root", "1234");
        }
    }

    private String generateGrnNumber() {
        String prefix = "GRN";
        String datePart = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());

        int randomNumber = (int) (Math.random() * 10000);  // 0000 - 9999
        String padded = String.format("%04d", randomNumber);  // always 4 digits

        return prefix + datePart + padded;
    }

    private String generateReferenceNumber() {
        String prefix = "REF";
        long timestamp = System.currentTimeMillis();  // unique millis

        return prefix + timestamp;
    }

    private void calculateTotalAmount() {
        try {
            double quantity = Double.parseDouble(txtQty.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            double total = quantity * unitPrice;
            txtTotal.setText(String.format("%.2f", total));
        } catch (NumberFormatException e) {
            // Invalid input: clear total
            txtTotal.setText("");
        }
    }

    private void addCalculationListeners() {
        txtQty.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                calculateTotalAmount();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                calculateTotalAmount();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                calculateTotalAmount();
            }
        });

        txtUnitPrice.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                calculateTotalAmount();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                calculateTotalAmount();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                calculateTotalAmount();
            }
        });
    }

    public class SupplierItem {

        private int id;
        private String name;

        public SupplierItem(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return name; // ComboBox එකේ පේන්න නම
        }
    }

    private void loadPaymentMethods() {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nexamarket", "root", "1234");
            String query = "SELECT id, name FROM paymentmethod";
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            cmbPaymentMethod.removeAllItems(); // Clear old items

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                ((javax.swing.JComboBox) cmbPaymentMethod).addItem(new PaymentMethodItem(id, name));
            }

            rs.close();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class PaymentMethodItem {

        private int id;
        private String name;

        public PaymentMethodItem(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return name; // ComboBox එකට පේන්න ඕන නම
        }
    }


    private void addRowToTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        String productName = txtProductName.getText();  // ✅ Newly added
        String itemDescription = txtItemDescription.getText();
        String itemCategory = cmbItemCategory.getSelectedItem().toString();
        String qty = txtQty.getText();
        String unitPrice = txtUnitPrice.getText();
        String total = txtTotal.getText();
//        String paymentMethod = cmbPaymentMethod.getSelectedItem().toString();

        Object[] row = {
            //            paymentMethod,
            productName,
            qty,
            unitPrice,
            total,
            itemDescription,
            itemCategory};

        model.addRow(row);

        clearInputFields();
        updateTotalFields();

    }

    String[] columnNames = {
        "Product Name",
        "Item Description",
        "Item Category",
        "Quantity",
        "Unit Price",
        "Expiry Date",
        "Total", //        "Payment Method"
    };

    private void updateTotalFields() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        int totalQty = 0;
        double totalPayment = 0.0;

        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                // ✔️ Column 8 = Quantity (as String like "12")
                String qtyStr = model.getValueAt(i, 1).toString().trim();
                int qty = Integer.parseInt(qtyStr);  // ✔️ OK
                totalQty += qty;
            } catch (NumberFormatException e) {
                System.err.println("Qty conversion error at row " + i + ": " + e.getMessage());
            }

            try {
                // ✔️ Column 10 = Total Amount (like "110.00")
                String totalStr = model.getValueAt(i, 3).toString().trim();
                double total = Double.parseDouble(totalStr);  // ✔️ Use Double
                totalPayment += total;
            } catch (NumberFormatException e) {
                System.err.println("Total amount conversion error at row " + i + ": " + e.getMessage());
            }
        }

        txtTotalQty.setText(String.valueOf(totalQty));
        txtTotalPayment.setText(String.format("%.2f", totalPayment));
    }

    private void clearInputFields() {
        txtProductName.setText("");
        txtItemDescription.setText("");
        cmbItemCategory.setSelectedIndex(0);
        txtQty.setText("");
        txtUnitPrice.setText("");
        txtTotal.setText("");
        cmbPaymentMethod.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        cmbPaymentMethod = new javax.swing.JComboBox<>();
        txtProductName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtUnitPrice = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cmbItemCategory = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        roundedButton1 = new dev.ramindu.nexamarket.components.RoundedButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtItemDescription = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtTotalQty = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtTotalPayment = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(1697, 833));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/home.png"))); // NOI18N
        jLabel2.setText("  Print Invoice");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Payment Method");

        cmbPaymentMethod.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPaymentMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPaymentMethodActionPerformed(evt);
            }
        });

        txtProductName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtProductName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductNameActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Item Name");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Quantity");

        txtQty.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtyActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("Unit Price");

        txtUnitPrice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtUnitPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitPriceActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Total Amout");

        txtTotal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Item Description");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("Item Cateogry");

        cmbItemCategory.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbItemCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbItemCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbItemCategoryActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        roundedButton1.setText("Add To Table");
        roundedButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Clear Fields");

        txtItemDescription.setColumns(20);
        txtItemDescription.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtItemDescription.setRows(5);
        jScrollPane2.setViewportView(txtItemDescription);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setText("Delete Recoard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel21.setText("Total Qty");

        txtTotalQty.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton2.setText("Prinit GRN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton4.setText("Go To Dashboard");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtTotalPayment.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel23.setText("Total Payment");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(cmbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(24, 24, 24)
                                .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtQty, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbItemCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(35, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(roundedButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(190, Short.MAX_VALUE))))))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1662, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(txtTotalPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(379, 379, 379)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel12)
                            .addComponent(cmbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(cmbItemCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundedButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23)
                                .addComponent(txtTotalPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel21)
                                .addComponent(txtTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteSelectedRow() {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow != -1) {
            // 🟡 Show confirmation dialog
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete the selected row?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                // ✅ User confirmed deletion
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.removeRow(selectedRow);
            }

        } else {
            // ❌ No row selected
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a row to delete.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );
        }
        updateTotalFields();

    }


    private void cmbPaymentMethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPaymentMethodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPaymentMethodActionPerformed

    private void txtProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductNameActionPerformed

    private void txtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtyActionPerformed

    private void txtUnitPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnitPriceActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void cmbItemCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbItemCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbItemCategoryActionPerformed

    private void roundedButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton1ActionPerformed
        // TODO add your handling code here:
        addRowToTable();
    }//GEN-LAST:event_roundedButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        deleteSelectedRow();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        generateInvoiceReport();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbItemCategory;
    private javax.swing.JComboBox<String> cmbPaymentMethod;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private dev.ramindu.nexamarket.components.RoundedButton roundedButton1;
    private javax.swing.JTextArea txtItemDescription;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalPayment;
    private javax.swing.JTextField txtTotalQty;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables
}
