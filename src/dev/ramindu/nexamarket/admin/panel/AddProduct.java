package dev.ramindu.nexamarket.admin.panel;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import static javax.swing.SwingConstants.CENTER;

public class AddProduct extends javax.swing.JDialog {

    private ProductSelectionListener listener;

    public void setProductSelectionListener(ProductSelectionListener listener) {
        this.listener = listener;
    }

    public AddProduct(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int selectedRow = jTable1.getSelectedRow();
                    if (selectedRow != -1 && listener != null) {
                        Object[] rowData = new Object[jTable1.getColumnCount()];
                        for (int i = 0; i < jTable1.getColumnCount(); i++) {
                            rowData[i] = jTable1.getValueAt(selectedRow, i);
                        }
                        listener.onProductSelected(rowData);
                        dispose();
                    }
                }
            }
        });
        // Setup table columns FIRST
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
            "ID", "Name", "Barcode", "Buying Price", "Selling Price", "Quantity", "Brand", "Category"
        });
        jTable1.setModel(model);

        loadProductData(); // Load product data after model set

        // Table header styling
        JTableHeader header = jTable1.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(0, 204, 102));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        jTable1.setRowHeight(32);
        jTable1.setShowGrid(false);
        jTable1.setIntercellSpacing(new Dimension(0, 0));
        jTable1.setBorder(null);
        ((JScrollPane) jTable1.getParent().getParent()).setBorder(null);
        jTable1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jTable1.setForeground(Color.BLACK);

        // Alternate row colors
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 245));
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(new Color(0, 120, 215));
                    c.setForeground(Color.WHITE);
                }
                setHorizontalAlignment(CENTER);
                return c;
            }
        };

        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    interface ProductSelectionListener {

        void onProductSelected(Object[] productData);
    }

    private void loadProductData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows

        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/nexamarket", "root", "1234");

            String sql = "SELECT p.id, p.name, p.barcode, p.Buying_Price, p.Selling_Price, p.quantity, "
                    + "b.name AS brand, c.name AS category "
                    + "FROM product p "
                    + "LEFT JOIN brand b ON p.brand_id = b.id "
                    + "LEFT JOIN category c ON p.category_id = c.id";

            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("barcode"),
                    rs.getString("Buying_Price"),
                    rs.getString("Selling_Price"),
                    rs.getInt("quantity"),
                    rs.getString("brand"),
                    rs.getString("category")
                };
                model.addRow(row);
            }

            rs.close();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Data Load Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Select Product");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/home.png")));
        jLabel2.setText("  Select Product");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    public static void main(String args[]) {
        FlatLightLaf.setup();

        java.awt.EventQueue.invokeLater(() -> {
            AddProduct dialog = new AddProduct(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration
}
