/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dev.ramindu.nexamarket.cashier.gui;

import dev.ramindu.nexamarket.main.gui.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import dev.ramindu.nexamarket.panel.AvailableStockPanel;
import dev.ramindu.nexamarket.panel.BranchRegistrationPanel;
import dev.ramindu.nexamarket.panel.BrandRegistrationPanel;
import dev.ramindu.nexamarket.panel.CateogryRegistrationPanel;
import dev.ramindu.nexamarket.panel.DashboardPanel;
import dev.ramindu.nexamarket.panel.ManageCustomersPanel;
import dev.ramindu.nexamarket.panel.ManageProductsPanel;
import dev.ramindu.nexamarket.panel.ProfilePanel;
import dev.ramindu.nexamarket.util.AppIconUtil;
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
    private ManageCustomersPanel managecustomerspanel;
    private ManageProductsPanel manageproductspanel;
    private BrandRegistrationPanel brandregistrationpanel;
    private CateogryRegistrationPanel cateogryregistrationpanel;
    private BranchRegistrationPanel branchregistrationpanel;
    private AvailableStockPanel availablestockpanel;
    private ProfilePanel profilepanel;

    private CardLayout ContentpanelLayout;

    public CashierHomeScreen() {
        initComponents();
        init();
        loadPanels();
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

        jTextField1.putClientProperty("JComponent.roundRect", true);
        UIManager.put("TextComponent.arc", 20);
        jTextField1.putClientProperty("JTextField.padding", new Insets(5, 20, 5, 10));

// Add placeholder text:
        jTextField1.putClientProperty("JTextField.placeholderText", "Search...");

        jButton1.putClientProperty("JButton.buttonType", "roundRect"); // enables rounded button
        jButton1.putClientProperty("JComponent.arc", 20);              // sets the border radius (20px)

        jButton2.putClientProperty("JButton.buttonType", "roundRect"); // enables rounded button
        jButton2.putClientProperty("JComponent.arc", 20);

        jButton3.putClientProperty("JButton.buttonType", "roundRect"); // enables rounded button
        jButton3.putClientProperty("JComponent.arc", 20);

        jButton4.putClientProperty("JButton.buttonType", "roundRect"); // enables rounded button
        jButton4.putClientProperty("JComponent.arc", 20);

        LocalDate today = LocalDate.now(); // get today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // format: 2025-06-13
        String formattedDate = "Today: " + today.format(formatter);

        DateTime.setText(formattedDate); // set button text

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

    private void loadPanels() {
        if (ContentPanel != null && ContentPanel.getLayout() instanceof CardLayout) {
            this.ContentpanelLayout = (CardLayout) ContentPanel.getLayout();
        } else {
            this.ContentpanelLayout = new CardLayout();
            ContentPanel.setLayout(this.ContentpanelLayout);
        }

        this.dashboardpanel = new DashboardPanel();
        this.managecustomerspanel = new ManageCustomersPanel();
        this.manageproductspanel = new ManageProductsPanel();
        this.brandregistrationpanel = new BrandRegistrationPanel();
        this.cateogryregistrationpanel = new CateogryRegistrationPanel();
        this.branchregistrationpanel = new BranchRegistrationPanel();
        this.availablestockpanel = new AvailableStockPanel();
        this.profilepanel = new ProfilePanel();

        this.dashboardpanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.managecustomerspanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");

        this.ContentPanel.add(dashboardpanel, "dashboard_panel");
        this.ContentPanel.add(managecustomerspanel, "managecustomerspanel");
        this.ContentPanel.add(manageproductspanel, "manageproductspanel");
        this.ContentPanel.add(brandregistrationpanel, "brandregistrationpanel");
        this.ContentPanel.add(cateogryregistrationpanel, "cateogryregistrationpanel");
        this.ContentPanel.add(branchregistrationpanel, "branchregistrationpanel");
        this.ContentPanel.add(availablestockpanel, "availablestockpanel");
        this.ContentPanel.add(profilepanel, "profilepanel");

        SwingUtilities.updateComponentTreeUI(ContentPanel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        DateTime = new dev.ramindu.nexamarket.components.RoundButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        Dashboard = new javax.swing.JLabel();
        Manage_Customers = new javax.swing.JLabel();
        Manage_Prodcuts = new javax.swing.JLabel();
        Brand_Registration = new javax.swing.JLabel();
        Cateogry_Registration = new javax.swing.JLabel();
        Branch_Registration = new javax.swing.JLabel();
        Available_Stock = new javax.swing.JLabel();
        Headline1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Good_Recieve_Notes = new javax.swing.JLabel();
        Previous_Recieve_Notes = new javax.swing.JLabel();
        Previous_Return_Notes = new javax.swing.JLabel();
        Headline2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        Good_Return_Notes = new javax.swing.JLabel();
        Previous_Invoice = new javax.swing.JLabel();
        Issue_Invoice = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        Headline3 = new javax.swing.JLabel();
        Profile = new javax.swing.JLabel();
        Sign_Out = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ContentPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        timer = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        Edit = new javax.swing.JMenu();
        View = new javax.swing.JMenu();
        Tools = new javax.swing.JMenu();
        Account = new javax.swing.JMenu();
        About = new javax.swing.JMenu();
        Help = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/logo (1).png"))); // NOI18N

        DateTime.setBackground(new java.awt.Color(204, 204, 204));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        Dashboard.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/home.png"))); // NOI18N
        Dashboard.setText("  Dashboard");
        Dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DashboardMouseClicked(evt);
            }
        });

        Manage_Customers.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Manage_Customers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/manage_customers.png"))); // NOI18N
        Manage_Customers.setText("  Manage Customers");
        Manage_Customers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Manage_Customers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Manage_CustomersMouseClicked(evt);
            }
        });

        Manage_Prodcuts.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Manage_Prodcuts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/manage_products.png"))); // NOI18N
        Manage_Prodcuts.setText("  Manage Products");
        Manage_Prodcuts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Manage_Prodcuts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Manage_ProdcutsMouseClicked(evt);
            }
        });

        Brand_Registration.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Brand_Registration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/tag.png"))); // NOI18N
        Brand_Registration.setText("  Brand Registration");
        Brand_Registration.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Brand_Registration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Brand_RegistrationMouseClicked(evt);
            }
        });

        Cateogry_Registration.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Cateogry_Registration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/cateogry.png"))); // NOI18N
        Cateogry_Registration.setText("  Cateogry Registration");
        Cateogry_Registration.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cateogry_Registration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cateogry_RegistrationMouseClicked(evt);
            }
        });

        Branch_Registration.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Branch_Registration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/branch.png"))); // NOI18N
        Branch_Registration.setText("  Branch Registration");
        Branch_Registration.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Branch_Registration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Branch_RegistrationMouseClicked(evt);
            }
        });

        Available_Stock.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Available_Stock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/stock.png"))); // NOI18N
        Available_Stock.setText("  Available Stock");
        Available_Stock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Available_Stock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Available_StockMouseClicked(evt);
            }
        });

        Headline1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Headline1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/grn.png"))); // NOI18N
        Headline1.setText(" Goods Recieve Notes");

        Good_Recieve_Notes.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Good_Recieve_Notes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/add.png"))); // NOI18N
        Good_Recieve_Notes.setText("  Issue Grn");
        Good_Recieve_Notes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Previous_Recieve_Notes.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Previous_Recieve_Notes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/correct.png"))); // NOI18N
        Previous_Recieve_Notes.setText("  Previous Grns");
        Previous_Recieve_Notes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Previous_Return_Notes.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Previous_Return_Notes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/correct.png"))); // NOI18N
        Previous_Return_Notes.setText("  Previous Grns");
        Previous_Return_Notes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Headline2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Headline2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/grn1.png"))); // NOI18N
        Headline2.setText(" Goods Return Notes");

        Good_Return_Notes.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Good_Return_Notes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/add.png"))); // NOI18N
        Good_Return_Notes.setText("  Issue Grn");
        Good_Return_Notes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Previous_Invoice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Previous_Invoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/correct.png"))); // NOI18N
        Previous_Invoice.setText("  Previous Invoice");
        Previous_Invoice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Issue_Invoice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Issue_Invoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/add.png"))); // NOI18N
        Issue_Invoice.setText("  Issue Invoice");
        Issue_Invoice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Headline3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Headline3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/invoice.png"))); // NOI18N
        Headline3.setText(" Invoices");

        Profile.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/user.png"))); // NOI18N
        Profile.setText("  Profile");
        Profile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProfileMouseClicked(evt);
            }
        });

        Sign_Out.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Sign_Out.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/signout.png"))); // NOI18N
        Sign_Out.setText("  Sign Out");
        Sign_Out.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Previous_Recieve_Notes)
                            .addComponent(Good_Recieve_Notes)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Headline3)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Previous_Invoice)
                                    .addComponent(Issue_Invoice)))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Headline2)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Previous_Return_Notes)
                                        .addComponent(Good_Return_Notes)))
                                .addComponent(Cateogry_Registration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Brand_Registration)
                                .addComponent(Available_Stock)
                                .addComponent(Branch_Registration)
                                .addComponent(Manage_Prodcuts)
                                .addComponent(Manage_Customers)
                                .addComponent(Dashboard)
                                .addComponent(Headline1)
                                .addComponent(jSeparator1)
                                .addComponent(jSeparator2))
                            .addComponent(Profile)
                            .addComponent(Sign_Out))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(Dashboard)
                .addGap(29, 29, 29)
                .addComponent(Manage_Customers)
                .addGap(30, 30, 30)
                .addComponent(Manage_Prodcuts)
                .addGap(26, 26, 26)
                .addComponent(Brand_Registration)
                .addGap(30, 30, 30)
                .addComponent(Cateogry_Registration)
                .addGap(27, 27, 27)
                .addComponent(Branch_Registration)
                .addGap(30, 30, 30)
                .addComponent(Available_Stock)
                .addGap(27, 27, 27)
                .addComponent(Headline1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Good_Recieve_Notes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Previous_Recieve_Notes)
                .addGap(18, 18, 18)
                .addComponent(Headline2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Good_Return_Notes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Previous_Return_Notes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Headline3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Issue_Invoice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Previous_Invoice)
                .addGap(18, 18, 18)
                .addComponent(Profile)
                .addGap(27, 27, 27)
                .addComponent(Sign_Out)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/profile.png"))); // NOI18N
        jLabel1.setText(" Ramindu Cashier");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Welcome Back");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Ramindu Ravihansa");

        jButton1.setBackground(new java.awt.Color(0, 204, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/setting.png"))); // NOI18N
        jButton1.setToolTipText("");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 204, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/notification.png"))); // NOI18N
        jButton2.setToolTipText("");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 204, 102));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/log-out.png"))); // NOI18N
        jButton3.setToolTipText("");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 204, 102));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/chat.png"))); // NOI18N
        jButton4.setToolTipText("");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        ContentPanel.setBackground(new java.awt.Color(255, 255, 255));
        ContentPanel.setLayout(new java.awt.CardLayout());
        jScrollPane1.setViewportView(ContentPanel);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1692, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        CashierChatScreen chat = new CashierChatScreen(this, true); // 'this' = parent frame
        chat.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void DashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardMouseClicked
        // TODO add your handling code here:
        this.ContentpanelLayout.show(ContentPanel, "dashboard_panel");
    }//GEN-LAST:event_DashboardMouseClicked

    private void Manage_CustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Manage_CustomersMouseClicked
        // TODO add your handling code here:
        this.ContentpanelLayout.show(ContentPanel, "managecustomerspanel");
    }//GEN-LAST:event_Manage_CustomersMouseClicked

    private void Manage_ProdcutsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Manage_ProdcutsMouseClicked
        // TODO add your handling code here:
        this.ContentpanelLayout.show(ContentPanel, "manageproductspanel");
    }//GEN-LAST:event_Manage_ProdcutsMouseClicked

    private void Brand_RegistrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Brand_RegistrationMouseClicked
        // TODO add your handling code here:
        this.ContentpanelLayout.show(ContentPanel, "brandregistrationpanel");

    }//GEN-LAST:event_Brand_RegistrationMouseClicked

    private void Cateogry_RegistrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cateogry_RegistrationMouseClicked
        // TODO add your handling code here:
        this.ContentpanelLayout.show(ContentPanel, "cateogryregistrationpanel");
    }//GEN-LAST:event_Cateogry_RegistrationMouseClicked

    private void Branch_RegistrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Branch_RegistrationMouseClicked
        // TODO add your handling code here:
        this.ContentpanelLayout.show(ContentPanel, "branchregistrationpanel");
    }//GEN-LAST:event_Branch_RegistrationMouseClicked

    private void Available_StockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Available_StockMouseClicked
        // TODO add your handling code here:
        this.ContentpanelLayout.show(ContentPanel, "availablestockpanel");
    }//GEN-LAST:event_Available_StockMouseClicked

    private void ProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileMouseClicked
        // TODO add your handling code here:
        this.ContentpanelLayout.show(ContentPanel, "profilepanel");
    }//GEN-LAST:event_ProfileMouseClicked

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
    private javax.swing.JLabel Available_Stock;
    private javax.swing.JLabel Branch_Registration;
    private javax.swing.JLabel Brand_Registration;
    private javax.swing.JLabel Cateogry_Registration;
    private javax.swing.JPanel ContentPanel;
    private javax.swing.JLabel Dashboard;
    private dev.ramindu.nexamarket.components.RoundButton DateTime;
    private javax.swing.JMenu Edit;
    private javax.swing.JMenu File;
    private javax.swing.JLabel Good_Recieve_Notes;
    private javax.swing.JLabel Good_Return_Notes;
    private javax.swing.JLabel Headline1;
    private javax.swing.JLabel Headline2;
    private javax.swing.JLabel Headline3;
    private javax.swing.JMenu Help;
    private javax.swing.JLabel Issue_Invoice;
    private javax.swing.JLabel Manage_Customers;
    private javax.swing.JLabel Manage_Prodcuts;
    private javax.swing.JLabel Previous_Invoice;
    private javax.swing.JLabel Previous_Recieve_Notes;
    private javax.swing.JLabel Previous_Return_Notes;
    private javax.swing.JLabel Profile;
    private javax.swing.JLabel Sign_Out;
    private javax.swing.JMenu Tools;
    private javax.swing.JMenu View;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel timer;
    // End of variables declaration//GEN-END:variables
}
