/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package dev.ramindu.nexamarket.components.menubar;

import dev.ramindu.nexamarket.model.User;
import java.text.ParseException;

import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;


import java.awt.CardLayout;


import com.formdev.flatlaf.FlatLightLaf;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

/**
 *
 * @author Ramindu
 */
public class RestoreDatabaseStep extends javax.swing.JDialog {

    private final User loggedInUser;

    public RestoreDatabaseStep(java.awt.Frame parent, boolean modal, User user) {
        super(parent, modal);
        this.loggedInUser = user;
        initComponents();
        registerPanels();

        
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, "step1");

        jButtonNext.setEnabled(false);
        jButtonNext.setText("Next");

     
        jListFiles.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                jButtonNext.setEnabled(jListFiles.getSelectedValue() != null);
            }
        });

    
        loadBackupDates();

        jListDates.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListDatesValueChanged(evt);
            }
        });
    }

    private void restoreDatabase(File backupFile) {
        try {
            System.out.println("[DEBUG] ===== Starting Database Restore =====");
            System.out.println("[DEBUG] Backup file: " + backupFile.getAbsolutePath());
            System.out.println("[DEBUG] File exists: " + backupFile.exists());
            System.out.println("[DEBUG] File size: " + backupFile.length() + " bytes");
            System.out.println("[DEBUG] File readable: " + backupFile.canRead());

           
            System.out.println("[DEBUG] Testing database connection...");
            try (Connection testConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/?useSSL=false",
                    "root",
                    "1234")) {
                System.out.println("[DEBUG] Database server connection successful");
            }

            
            System.out.println("[DEBUG] Connecting to nexaMarket database...");
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nexamarket?useSSL=false",
                    "root",
                    "1234")) {

                System.out.println("[DEBUG] Database connection established");

                
                System.out.println("[DEBUG] Reading backup file...");
                String sqlContent = new String(Files.readAllBytes(backupFile.toPath()), StandardCharsets.UTF_8);

             
                System.out.println("[DEBUG] Processing SQL statements...");
                try (Statement stmt = conn.createStatement()) {
                    
                    stmt.execute("SET FOREIGN_KEY_CHECKS=0");

                   
                    String[] statements = sqlContent.split(";(?=(?:[^']*'[^']*')*[^']*$)");
                    System.out.println("[DEBUG] Found " + statements.length + " statements");

                    for (int i = 0; i < statements.length; i++) {
                        String statement = statements[i].trim();

                    
                        if (statement.isEmpty()
                                || statement.startsWith("/*!")
                                || statement.startsWith("--")
                                || statement.matches("^\\s*$")) {
                            continue;
                        }

                        try {
                            System.out.println("[DEBUG] Executing statement #" + (i + 1)
                                    + " (first 50 chars): " + statement.substring(0, Math.min(50, statement.length())));
                            stmt.execute(statement);
                        } catch (SQLException e) {
                            System.err.println("[ERROR] Failed to execute statement #" + (i + 1));
                            System.err.println("[ERROR] SQL: " + statement);
                            System.err.println("[ERROR] Message: " + e.getMessage());
                            throw e;
                        }
                    }

                    stmt.execute("SET FOREIGN_KEY_CHECKS=1");
                }

                System.out.println("[DEBUG] Database restore completed successfully");
                JOptionPane.showMessageDialog(this,
                        "Database restored successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                jButtonNext.setText("Close");
                jButton6.setVisible(false);
            }
        } catch (Exception ex) {
            System.err.println("[ERROR] Restore process failed:");
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Restoration failed: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registerPanels() {
      
        cardPanel.add(step1Panel, "step1");
        cardPanel.add(step2Panel, "step2");
    }

    private void jListDatesValueChanged(javax.swing.event.ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
            String selectedDate = jListDates.getSelectedValue();
            if (selectedDate != null) {
               
                try {
                    SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat filenameFormat = new SimpleDateFormat("yyyyMMdd");
                    Date date = displayFormat.parse(selectedDate);
                    String dateForFilename = filenameFormat.format(date);
                    loadBackupsForDate(dateForFilename);
                } catch (ParseException e) {
                    System.err.println("Error parsing date: " + selectedDate);
                }
            }
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

        cardPanel = new javax.swing.JPanel();
        step1Panel = new javax.swing.JPanel();
        Panel = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel58 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        step2Panel = new javax.swing.JPanel();
        Panel1 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel61 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel62 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListFiles = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListDates = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Database Restoration");
        setResizable(false);

        cardPanel.setLayout(new java.awt.CardLayout());

        step1Panel.setPreferredSize(new java.awt.Dimension(800, 573));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/logo (1).png"))); // NOI18N
        jLabel55.setToolTipText("");

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel56.setText("Steps");

        jLabel57.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel57.setText("1. License Agrreement");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel55))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56)
                            .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                .addComponent(jLabel55)
                .addGap(21, 21, 21))
        );

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel58.setText("Data Recovery Agrreement");

        jLabel2.setText("Press Page Down to see the rest of user agreement");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("DATABASE RESTORATION AGREEMENT\n\nBefore proceeding with the database restoration, please carefully read and \nunderstand the following agreement. This process is critical and may affect \nthe integrity of your existing system data.\n\n1. By initiating this process, you acknowledge and accept full responsibility for\n any data changes made as a result of the restoration.\n\n2. This operation will replace the current database with a previous backup. \nAny data added after the backup date will be permanently lost.\n\n3. It is strongly advised that you create a fresh backup of the current database\n before continuing.\n\n4. You confirm that the backup file you intend to use:\n   - Is complete and uncorrupted.\n   - Matches the version and structure of the current POS system.\n   - Has not been modified manually or tampered with.\n\n5. You agree that the developers, support personnel, or company providing the \nsoftware shall not be liable for:\n   - Any data loss or corruption.\n   - Incompatibility issues caused by outdated or invalid backups.\n   - Misuse of the restoration process.\n\n6. This tool is intended for use by authorized personnel only. Unauthorized \nrestoration attempts may lead to permanent data damage or legal consequences.\n\n7. The restoration process may take a few minutes. Please ensure:\n   - The system remains powered on.\n   - No other operations are performed during the restoration.\n\n8. After successful restoration, the system will restart automatically, and all\n  users will be logged out.\n\n By clicking \"Next\" or continuing, you confirm that you have read, understood, \nand agreed to the above conditions. Proceed only if you are confident about\n the backup file and are aware of the consequences.\n");
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("If You Agree Our Terms And Conditions Please Click The Agree Button To");

        jLabel3.setText(" Continue Your Database Restoration");

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator10)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jLabel3))
        );

        jButton4.setText("Back");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Agree");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout step1PanelLayout = new javax.swing.GroupLayout(step1Panel);
        step1Panel.setLayout(step1PanelLayout);
        step1PanelLayout.setHorizontalGroup(
            step1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step1PanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(17, 17, 17))
        );
        step1PanelLayout.setVerticalGroup(
            step1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(step1PanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(step1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step1PanelLayout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addContainerGap())))
        );

        cardPanel.add(step1Panel, "card3");

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/logo (1).png"))); // NOI18N
        jLabel59.setToolTipText("");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel60.setText("Steps");

        jLabel61.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel61.setText("1. License Agrreement");

        jLabel63.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel63.setText("1. Choose Recovery Date");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel59))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel60)
                            .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61)
                            .addComponent(jLabel63))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel61)
                .addGap(18, 18, 18)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                .addComponent(jLabel59)
                .addGap(21, 21, 21))
        );

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel62.setText("Select Recovery Date");

        jLabel71.setText("Filter : ");

        jLabel4.setText("Pick Date");

        jLabel5.setText("Pick Time");

        jListFiles.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Please Select Date" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jListFiles);

        jListDates.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jListDates);

        jLabel6.setText("Thanks For Agree With Our Terms & Conditions");

        javax.swing.GroupLayout Panel1Layout = new javax.swing.GroupLayout(Panel1);
        Panel1.setLayout(Panel1Layout);
        Panel1Layout.setHorizontalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel1Layout.createSequentialGroup()
                        .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel62)
                            .addGroup(Panel1Layout.createSequentialGroup()
                                .addComponent(jLabel71)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator12))
                        .addContainerGap())
                    .addGroup(Panel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel1Layout.createSequentialGroup()
                                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22))
                            .addGroup(Panel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        Panel1Layout.setVerticalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6))
        );

        jButton6.setText("Back");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButtonNext.setText("Next");
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout step2PanelLayout = new javax.swing.GroupLayout(step2Panel);
        step2Panel.setLayout(step2PanelLayout);
        step2PanelLayout.setHorizontalGroup(
            step2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step2PanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButtonNext)
                .addGap(17, 17, 17))
        );
        step2PanelLayout.setVerticalGroup(
            step2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(step2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(step2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNext)
                    .addComponent(jButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardPanel.add(step2Panel, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        ConfirmPassword passwordDialog = new ConfirmPassword(
                (java.awt.Frame) this.getParent(),
                true,
                loggedInUser
        );
        passwordDialog.setVisible(true);

        if (passwordDialog.isPasswordCorrect()) {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "step2");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, "step1");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        // TODO add your handling code here:

        System.out.println("[DEBUG] Next button clicked - Text: " + jButtonNext.getText());

        if (jButtonNext.getText().equals("Next")) {
            System.out.println("[DEBUG] Starting restore process");
            String selectedFile = jListFiles.getSelectedValue();
            System.out.println("[DEBUG] Selected file: " + selectedFile);

            if (selectedFile != null) {
                File backupFile = new File("F:/Java_Projects/Nexa_Market/src/dev/ramindu/nexamarket/backups/" + selectedFile.split(" - ")[1]);
                System.out.println("[DEBUG] Full backup path: " + backupFile.getAbsolutePath());

                if (backupFile.exists()) {
                    System.out.println("[DEBUG] Backup file exists, showing confirmation");
                    int confirm = JOptionPane.showConfirmDialog(
                            this,
                            "Restore from " + selectedFile + "? This will overwrite current data!",
                            "Confirm Restoration",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        System.out.println("[DEBUG] User confirmed, starting restore");
                        restoreDatabase(backupFile);
                    }
                } else {
                    System.err.println("[ERROR] Backup file does not exist at: " + backupFile.getAbsolutePath());
                }
            }
        } else {
            System.out.println("[DEBUG] Closing dialog");
            this.dispose();
        }
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void loadBackupDates() {
        File backupDir = new File("F:/Java_Projects/Nexa_Market/src/dev/ramindu/nexamarket/backups");
        DefaultListModel<String> dateModel = new DefaultListModel<>();
        Map<String, String> dateMap = new HashMap<>(); 

        if (backupDir.exists() && backupDir.isDirectory()) {
            File[] files = backupDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    String name = file.getName();
                    if (name.startsWith("nexamarket_") && name.endsWith(".sql")) {
                        String[] parts = name.split("_");
                        if (parts.length >= 2) {
                            String dateStr = parts[1]; // yyyyMMdd format
                            try {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                Date date = sdf.parse(dateStr);
                                String displayDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

                                if (!dateMap.containsKey(displayDate)) {
                                    dateModel.addElement(displayDate);
                                    dateMap.put(displayDate, dateStr);
                                }
                            } catch (ParseException e) {
                                System.err.println("Invalid date format: " + name);
                            }
                        }
                    }
                }
            }
        }

        jListDates.setModel(dateModel);
        jListDates.putClientProperty("dateMap", dateMap);
    }

    private void loadBackupsForDate(String selectedDate) {
        File backupDir = new File("F:/Java_Projects/Nexa_Market/src/dev/ramindu/nexamarket/backups");
        System.out.println("[DEBUG] Looking for backups in: " + backupDir.getAbsolutePath());

        DefaultListModel<String> model = new DefaultListModel<>();

        if (backupDir.exists() && backupDir.isDirectory()) {
            System.out.println("[DEBUG] Backup directory exists");
            File[] files = backupDir.listFiles();

            if (files != null) {
                System.out.println("[DEBUG] Found " + files.length + " files in directory");
                Arrays.sort(files, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));

                for (File file : files) {
                    String name = file.getName();
                    System.out.println("[DEBUG] Checking file: " + name);

                    if (name.startsWith("nexamarket_" + selectedDate) && name.endsWith(".sql")) {
                        String timePart = name.split("_")[2].replace(".sql", "");
                        try {
                            String displayTime = timePart.substring(0, 2) + ":"
                                    + timePart.substring(2, 4) + ":"
                                    + timePart.substring(4, 6);
                            model.addElement(displayTime + " - " + name);
                            System.out.println("[DEBUG] Added backup: " + name);
                        } catch (Exception e) {
                            model.addElement(name);
                        }
                    }
                }
            }
        } else {
            System.err.println("[ERROR] Backup directory does not exist or is not accessible");
        }

        if (model.isEmpty()) {
            System.out.println("[DEBUG] No backups found for date: " + selectedDate);
            model.addElement("No backups found for " + selectedDate);
        }

        jListFiles.setModel(model);
        jButtonNext.setEnabled(false);
        jButtonNext.setText("Next");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
        FlatLightLaf.setup();

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             
                RestoreDatabaseStep dialog = new RestoreDatabaseStep(null, false, null);

             
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });

               
                dialog.setLocationRelativeTo(null);

                
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JPanel Panel1;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JList<String> jListDates;
    private javax.swing.JList<String> jListFiles;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel step1Panel;
    private javax.swing.JPanel step2Panel;
    // End of variables declaration//GEN-END:variables
}
