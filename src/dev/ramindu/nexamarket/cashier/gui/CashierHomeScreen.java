/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dev.ramindu.nexamarket.cashier.gui;

import dev.ramindu.nexamarket.main.gui.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import dev.ramindu.nexamarket.admin.panel.AvailableStockPanel;
import dev.ramindu.nexamarket.admin.panel.BranchRegistrationPanel;
import dev.ramindu.nexamarket.admin.panel.BrandRegistrationPanel;
import dev.ramindu.nexamarket.admin.panel.CateogryRegistrationPanel;
import dev.ramindu.nexamarket.admin.panel.DashboardPanel;
import dev.ramindu.nexamarket.admin.panel.ManageCashiersPanel;
import dev.ramindu.nexamarket.admin.panel.ManageProductsPanel;
import dev.ramindu.nexamarket.admin.panel.ProfilePanel;
import dev.ramindu.nexamarket.utils.AppIconUtil;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Ramindu
 */
public class CashierHomeScreen extends javax.swing.JFrame {

    /**
     * Creates new form CashierHomeScreen
     */
    private final java.time.Instant appStartTime = java.time.Instant.now();

    private DashboardPanel dashboardpanel;
    private ManageCashiersPanel managecustomerspanel;
    private ManageProductsPanel manageproductspanel;
    private BrandRegistrationPanel brandregistrationpanel;
    private CateogryRegistrationPanel cateogryregistrationpanel;
    private BranchRegistrationPanel branchregistrationpanel;
    private AvailableStockPanel availablestockpanel;
    private ProfilePanel profilepanel;
    
        private String loggedInUserName;

    private CardLayout ContentpanelLayout;

    public CashierHomeScreen(String userName) {
        initComponents();
        init();
    
             this.loggedInUserName = userName;
    }
     public CashierHomeScreen() {
      
    }

    private void init() {
        AppIconUtil.Applyicon(this);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // 🧹 Clear all default items from the JMenuBar
        jMenuBar1.removeAll();

        // ✅ Add a horizontal strut to create space between logo and 'File'
        jMenuBar1.add(Box.createHorizontalStrut(10)); // 👈 this is what was missing

        // ✅ Add menus with spacing
        jMenuBar1.add(File);
        jMenuBar1.add(Box.createHorizontalStrut(10));
        jMenuBar1.add(Edit);
        jMenuBar1.add(Box.createHorizontalStrut(10));
        jMenuBar1.add(View);
        jMenuBar1.add(Box.createHorizontalStrut(10));
        jMenuBar1.add(Tools);
        jMenuBar1.add(Box.createHorizontalStrut(10));
        jMenuBar1.add(Account);
        jMenuBar1.add(Box.createHorizontalStrut(10));
        jMenuBar1.add(About);
        jMenuBar1.add(Box.createHorizontalStrut(10));
        jMenuBar1.add(Help);

        // ✅ Centered title
        jMenuBar1.add(Box.createHorizontalGlue());

        JLabel titleLabel = new JLabel("Nexa Market");
        titleLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(Color.GRAY);
        jMenuBar1.add(titleLabel);

        jMenuBar1.add(Box.createHorizontalGlue());

        // 🔁 Refresh UI
        jMenuBar1.revalidate();
        jMenuBar1.repaint();

        // Place this after initComponents(); in your constructor
        javax.swing.Timer clockAndUptime = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Get current time
                java.time.LocalTime now = java.time.LocalTime.now();
                java.time.format.DateTimeFormatter timeFormatter = java.time.format.DateTimeFormatter.ofPattern("hh:mm:ss a");

                // Calculate uptime
                java.time.Duration elapsed = java.time.Duration.between(appStartTime, java.time.Instant.now());
                long hours = elapsed.toHours();
                long minutes = elapsed.toMinutes() % 60;
                long seconds = elapsed.getSeconds() % 60;

                String formattedTime = now.format(timeFormatter);
                String formattedUptime = String.format("Uptime: %02d:%02d:%02d", hours, minutes, seconds);

                // Combine with pipe symbol
                String displayText = formattedTime + " | " + formattedUptime;
                timer.setText(displayText); // 'timer' is your JLabel name
            }
        });
        clockAndUptime.start();

    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        timer = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        Edit = new javax.swing.JMenu();
        View = new javax.swing.JMenu();
        Tools = new javax.swing.JMenu();
        Account = new javax.swing.JMenu();
        About = new javax.swing.JMenu();
        Help = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(59, 25));

        timer.setText("1:42:28 PM");

        jLabel5.setText("ENG");

        jLabel7.setText("UTF - 8");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/forward.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/notification-small.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/about.png"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/menu.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(timer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1600, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(41, 41, 41)
                .addComponent(jLabel7)
                .addGap(22, 22, 22)
                .addComponent(jLabel8)
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(48, 48, 48))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(timer)
                            .addComponent(jLabel11))))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );

        File.setText("File");
        jMenuBar1.add(File);

        Edit.setText("Edit");
        jMenuBar1.add(Edit);

        View.setText("View");
        jMenuBar1.add(View);

        Tools.setText("Tools");
        jMenuBar1.add(Tools);

        Account.setText("Account");
        jMenuBar1.add(Account);

        About.setText("About");
        jMenuBar1.add(About);

        Help.setText("Help");
        jMenuBar1.add(Help);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1939, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        FlatLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CashierHomeScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu About;
    private javax.swing.JMenu Account;
    private javax.swing.JMenu Edit;
    private javax.swing.JMenu File;
    private javax.swing.JMenu Help;
    private javax.swing.JMenu Tools;
    private javax.swing.JMenu View;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel timer;
    // End of variables declaration//GEN-END:variables
}
