/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dev.ramindu.nexamarket.cashier.gui;

import dev.ramindu.nexamarket.main.gui.*;
import com.formdev.flatlaf.FlatLightLaf;
import dev.ramindu.nexamarket.admin.panel.IssueInvoicePanel;
import dev.ramindu.nexamarket.admin.panel.PeviousInvoice;
import dev.ramindu.nexamarket.admin.panel.ProfilePanel;
import dev.ramindu.nexamarket.components.menubar.AboutPage;
import dev.ramindu.nexamarket.model.User;
import dev.ramindu.nexamarket.utils.AppIconUtil;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import dev.ramindu.nexamarket.components.menubar.DeveloperCredits;
import dev.ramindu.nexamarket.components.menubar.RestoreDatabaseStep;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import dev.ramindu.nexamarket.components.menubar.UpdateChecking;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.swing.JPanel;

/**
 *
 * @author Ramindu
 */
public class CashierHomeScreen extends javax.swing.JFrame {

    /**
     * Creates new form CashierHomeScreen
     */
    private final java.time.Instant appStartTime = java.time.Instant.now();


    private ProfilePanel profilepanel;
    private PeviousInvoice previousinvoices;
    private IssueInvoicePanel IssueInvoicePanel;

    private CardLayout ContentpanelLayout;

    private Map<String, JPanel> panelMap = new HashMap<>();

    private CardLayout cardLayout;
    private Map<String, String> panelKeys = new HashMap<>();

    private User loggedInUser;

    private Dimension originalSize;
    private Point originalLocation;
    private boolean isFullScreen = false;

    public CashierHomeScreen(User user) {
        this.loggedInUser = user;
        initComponents();
        init();

        
        this.ContentpanelLayout = (CardLayout) ContentPanel.getLayout();

       
        initPanelMap();
        loadPanels();

        
        String fullName = user.getFirstName() + " " + user.getLastName();
        jLabel1.setText("Welcome, " + fullName);

        System.out.println("New window opened for: " + loggedInUser.getFirstName());
        jMenuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        jMenuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        jMenuItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));

        jRadioButtonMenuItem1.setText("Full Screen");  // Initial text
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });

        jRadioButtonMenuItem1.setSelected(false);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (originalSize == null) {
            originalSize = getSize();
            originalLocation = getLocation();
        }
    }

    public CashierHomeScreen() {
        initComponents();
        init();
        loadPanels();
        initPanelMap();

    }

    private void toggleFullScreen() {

        if (!isFullScreen) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "This will hide the Windows taskbar. Continue?",
                    "Full Screen Mode",
                    JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
        }
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();

        if (!isFullScreen) {
       
            setExtendedState(JFrame.NORMAL); 
            dispose();
            setUndecorated(true); 
            setVisible(true); 
            gd.setFullScreenWindow(this); 
            isFullScreen = true;
            jRadioButtonMenuItem1.setText("Exit Full Screen");
            toggleTaskbar(false); 
        } else {
         
            gd.setFullScreenWindow(null); 
            dispose(); 
            setUndecorated(false); 
            setExtendedState(JFrame.MAXIMIZED_BOTH); 
            setVisible(true); 
            isFullScreen = false;
            jRadioButtonMenuItem1.setText("Full Screen");
            toggleTaskbar(true); 
        }
    }

    private void toggleTaskbar(boolean show) {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            try {
                if (show) {
                  
                    Runtime.getRuntime().exec("cmd /c start explorer.exe");
                } else {
                   
                    Runtime.getRuntime().exec("cmd /c taskkill /f /im explorer.exe");

                    
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Could not toggle taskbar: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }

    private void init() {
        AppIconUtil.Applyicon(this);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

       
        jMenuBar1.removeAll();

     
        jMenuBar1.add(Box.createHorizontalStrut(10)); // 👈 this is what was missing

    
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

       
        jMenuBar1.add(Box.createHorizontalGlue());

        JLabel titleLabel = new JLabel("Nexa Market");
        titleLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(Color.GRAY);
        jMenuBar1.add(titleLabel);

        jMenuBar1.add(Box.createHorizontalGlue());

       
        jMenuBar1.revalidate();
        jMenuBar1.repaint();

        jTextField1.putClientProperty("JComponent.roundRect", true);
        UIManager.put("TextComponent.arc", 20);
        jTextField1.putClientProperty("JTextField.padding", new Insets(5, 20, 5, 10));

        jTextField1.putClientProperty("JTextField.placeholderText", "Search...");

        jButton1.putClientProperty("JButton.buttonType", "roundRect");
        jButton1.putClientProperty("JComponent.arc", 20);             

        jButton2.putClientProperty("JButton.buttonType", "roundRect");
        jButton2.putClientProperty("JComponent.arc", 20);

        jButton3.putClientProperty("JButton.buttonType", "roundRect"); 
        jButton3.putClientProperty("JComponent.arc", 20);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
        String formattedDate = "Today: " + today.format(formatter);

        DateTime.setText(formattedDate); 

  
        javax.swing.Timer clockAndUptime = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
              
                java.time.LocalTime now = java.time.LocalTime.now();
                java.time.format.DateTimeFormatter timeFormatter = java.time.format.DateTimeFormatter.ofPattern("hh:mm:ss a");

                
                java.time.Duration elapsed = java.time.Duration.between(appStartTime, java.time.Instant.now());
                long hours = elapsed.toHours();
                long minutes = elapsed.toMinutes() % 60;
                long seconds = elapsed.getSeconds() % 60;

                String formattedTime = now.format(timeFormatter);
                String formattedUptime = String.format("Uptime: %02d:%02d:%02d", hours, minutes, seconds);

         
                String displayText = formattedTime + " | " + formattedUptime;
                timer.setText(displayText); // 'timer' is your JLabel name
            }
        });
        clockAndUptime.start();

    }

    private void initPanelMap() {
       
        panelKeys.clear();

        
        panelKeys.put("issue invoice", "IssueInvoicePanel");
        panelKeys.put("previous invoice", "previousinvoices");
        panelKeys.put("profile", "profilepanel");

       
        panelKeys.put("grn", "issuegrnpanel");
        panelKeys.put("invoice", "IssueInvoicePanel");
    }

    private void addPanel(String key, JPanel panel) {
        ContentPanel.add(panel, key.toLowerCase());
        panelKeys.put(key.toLowerCase(), key.toLowerCase());
    }

    private void loadPanels() {
        if (ContentPanel != null && ContentPanel.getLayout() instanceof CardLayout) {
            this.ContentpanelLayout = (CardLayout) ContentPanel.getLayout();
        } else {
            this.ContentpanelLayout = new CardLayout();
            ContentPanel.setLayout(this.ContentpanelLayout);
        }

        this.profilepanel = new ProfilePanel(loggedInUser);
        this.previousinvoices = new PeviousInvoice();
        this.IssueInvoicePanel = new IssueInvoicePanel();

        this.ContentPanel.add(IssueInvoicePanel, "IssueInvoicePanel");
        this.ContentPanel.add(profilepanel, "profilepanel");
        this.ContentPanel.add(previousinvoices, "previousinvoices");


        SwingUtilities.updateComponentTreeUI(ContentPanel);
    }

    public void openWindowsCalculator() {
        try {
            ProcessBuilder pb = new ProcessBuilder("calc.exe");
            pb.start();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Cannot open calculator!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        Previous_Invoice = new javax.swing.JLabel();
        Issue_Invoice = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        Headline3 = new javax.swing.JLabel();
        Profile = new javax.swing.JLabel();
        Sign_Out = new javax.swing.JLabel();
        DateTime = new dev.ramindu.nexamarket.components.RoundButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        Edit = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        View = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        Tools = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        Account = new javax.swing.JMenu();
        jMenuItem26 = new javax.swing.JMenuItem();
        About = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        Help = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/logo (1).png"))); // NOI18N

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        Previous_Invoice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Previous_Invoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/correct.png"))); // NOI18N
        Previous_Invoice.setText("  Previous Invoice");
        Previous_Invoice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Previous_Invoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Previous_InvoiceMouseClicked(evt);
            }
        });

        Issue_Invoice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Issue_Invoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/add.png"))); // NOI18N
        Issue_Invoice.setText("  Issue Invoice");
        Issue_Invoice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Issue_Invoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Issue_InvoiceMouseClicked(evt);
            }
        });

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
        Sign_Out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sign_OutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Headline3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Previous_Invoice)
                            .addComponent(Issue_Invoice)))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Profile)
                    .addComponent(Sign_Out))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Headline3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Issue_Invoice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Previous_Invoice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 567, Short.MAX_VALUE)
                .addComponent(Profile)
                .addGap(27, 27, 27)
                .addComponent(Sign_Out)
                .addGap(102, 102, 102))
        );

        jScrollPane2.setViewportView(jPanel3);

        DateTime.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dev/ramindu/nexamarket/img/profile.png"))); // NOI18N
        jLabel1.setText(" Ramindu");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Welcome Back");

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

        jMenuItem1.setText("New Window");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        File.add(jMenuItem1);

        jMenuItem2.setText("Print Screen");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        File.add(jMenuItem2);

        jMenuItem3.setText("Log Out");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        File.add(jMenuItem3);

        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        File.add(jMenuItem4);

        jMenuBar1.add(File);

        Edit.setText("Edit");

        jMenuItem5.setText("Undo");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        Edit.add(jMenuItem5);

        jMenuItem6.setText("Redo");
        Edit.add(jMenuItem6);

        jMenuItem7.setText("Cut");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        Edit.add(jMenuItem7);

        jMenuItem8.setText("Copy");
        Edit.add(jMenuItem8);

        jMenuItem9.setText("Paste");
        Edit.add(jMenuItem9);

        jMenuItem10.setText("Delete Selected");
        Edit.add(jMenuItem10);

        jMenuItem11.setText("Edit Profile");
        Edit.add(jMenuItem11);

        jMenuItem12.setText("Change Password");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        Edit.add(jMenuItem12);

        jMenuBar1.add(Edit);

        View.setText("View");
        View.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewActionPerformed(evt);
            }
        });

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("Full Screen");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        View.add(jRadioButtonMenuItem1);

        jMenuBar1.add(View);

        Tools.setText("Tools");

        jMenuItem13.setText("Calculator");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        Tools.add(jMenuItem13);

        jMenuItem14.setText("Backup Database");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        Tools.add(jMenuItem14);

        jMenuItem15.setText("Restore Database");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        Tools.add(jMenuItem15);

        jMenuItem16.setText("Update Software");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        Tools.add(jMenuItem16);

        jMenuBar1.add(Tools);

        Account.setText("Account");

        jMenuItem26.setText("Sign Out");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        Account.add(jMenuItem26);

        jMenuBar1.add(Account);

        About.setText("About");

        jMenuItem17.setText("About Application");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        About.add(jMenuItem17);

        jMenuItem19.setText("Developer Credits");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        About.add(jMenuItem19);

        jMenuBar1.add(About);

        Help.setText("Help");

        jMenu1.setText("Contact Support");

        jMenuItem23.setText("Email");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem23);

        Help.add(jMenu1);

        jMenuItem24.setText("Check Updates");
        Help.add(jMenuItem24);

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
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 1150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
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
        this.setVisible(false);

        
        LoginScreen login = new LoginScreen(null, true); // 'null' as parent if modal
        login.setLocationRelativeTo(null); // Center on screen
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileMouseClicked
        // TODO add your handling code here:
        this.ContentpanelLayout.show(ContentPanel, "profilepanel");
    }//GEN-LAST:event_ProfileMouseClicked

    private void Issue_InvoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Issue_InvoiceMouseClicked
        // TODO add your handling code here:
        this.ContentpanelLayout.show(ContentPanel, "IssueInvoicePanel");
    }//GEN-LAST:event_Issue_InvoiceMouseClicked

    private void Previous_InvoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Previous_InvoiceMouseClicked
        // TODO add your handling code here:
        this.ContentpanelLayout.show(ContentPanel, "previousinvoices");
    }//GEN-LAST:event_Previous_InvoiceMouseClicked


    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        CashierHomeScreen newWindow = new CashierHomeScreen(loggedInUser);
        newWindow.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        openWindowsCalculator();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(
                this,
                "Do you really want to log out?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {
            
            this.dispose();

 
            LoginScreen login = new LoginScreen(new Frame(), true);
            login.setVisible(true);

        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:

        int option = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
        // TODO add your handling code here:
        if (jRadioButtonMenuItem1.isSelected()) {
        
            dispose(); 
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setUndecorated(true);
            setVisible(true); 
            isFullScreen = true;
        } else {
           
            dispose();
            setExtendedState(JFrame.NORMAL);
            setUndecorated(false);
            setSize(originalSize);
            setLocation(originalLocation);
            setVisible(true);
            isFullScreen = false;
        }
    }//GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed

    private void ViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        new AboutPage(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void Sign_OutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sign_OutMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);

        LoginScreen login = new LoginScreen(null, true); // 'null' as parent if modal
        login.setLocationRelativeTo(null); // Center on screen
        login.setVisible(true);

        
        this.dispose();

    }//GEN-LAST:event_Sign_OutMouseClicked

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:
        new DeveloperCredits(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        new Thread(new Runnable() {
            @Override
            public void run() {
                databasebackup();
            }
        }).start();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        new RestoreDatabaseStep(this, true, loggedInUser).setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        new UpdateChecking(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        String searchText = jTextField1.getText().trim().toLowerCase();

       
        if (panelKeys.containsKey(searchText)) {
            String panelName = panelKeys.get(searchText);
            ContentpanelLayout.show(ContentPanel, panelName);
            return;
        }

    
        List<String> matches = panelKeys.keySet().stream()
                .filter(key -> key.contains(searchText))
                .collect(Collectors.toList());

        if (!matches.isEmpty()) {
       
            if (matches.size() == 1) {
                ContentpanelLayout.show(ContentPanel, panelKeys.get(matches.get(0)));
            } else {
               
                String options = String.join("\n", matches);
                JOptionPane.showMessageDialog(this,
                        "Multiple panels match your search:\n" + options,
                        "Search Results",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "No panel found matching: " + searchText + "\n\n"
                    + "Try one of these:\n"
                    + "- dashboard\n"
                    + "- manage products\n"
                    + "- available stock\n"
                    + "- issue invoice",
                    "Search",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        String prefix = jTextField1.getText().toLowerCase();
        if (prefix.length() > 2) {
            List<String> matches = panelKeys.keySet().stream()
                    .filter(key -> key.contains(prefix))
                    .collect(Collectors.toList());

            if (!matches.isEmpty()) {
              
            }
        }
    }//GEN-LAST:event_jTextField1KeyTyped
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           
            jTextField1ActionPerformed(null);
        }
    }
    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);

    
        LoginScreen login = new LoginScreen(null, true); // 'null' as parent if modal
        login.setLocationRelativeTo(null); // Center on screen
        login.setVisible(true);

       
        this.dispose();

    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void databasebackup() {
        String dumpPath = "C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump";
        String database = "nexamarket";
        String host = "localhost";
        String port = "3306";
        String username = "root";
        String password = "1234";

        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String backupDir = "F:/Java_Projects/Nexa_Market/src/dev/ramindu/nexamarket/backups";
            String backupFileName = backupDir + "/" + database + "_" + timestamp + ".sql";

         
            new File(backupDir).mkdirs();

          
            List<String> command = Arrays.asList(
                    dumpPath,
                    "--host=" + host,
                    "--port=" + port,
                    "-u", username,
                    "--password=" + password,
                    "--default-character-set=utf8mb4",
                    "--no-tablespaces",
                    "--skip-comments",
                    "--replace", 
                    "--result-file=" + backupFileName,
                    database
            );
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);  

            System.out.println("[DEBUG] Starting backup process...");
            Process process = builder.start();

          
            boolean finished = process.waitFor(30, TimeUnit.SECONDS);

            if (finished && process.exitValue() == 0) {
                System.out.println("✅ Backup Created Successfully: " + backupFileName);
                System.out.println("[DEBUG] Database restore completed successfully");
                JOptionPane.showMessageDialog(this,
                        "Database Backup successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                
                File backupFile = new File(backupFileName);
                if (backupFile.exists() && backupFile.length() > 0) {
                    System.out.println("[DEBUG] Backup file verified (" + backupFile.length() + " bytes)");
                } else {
                    throw new IOException("Backup file was not created properly");
                }
            } else {
                if (!finished) {
                    process.destroyForcibly();
                    throw new IOException("Backup process timed out");
                } else {
                    throw new IOException("Backup failed with exit code: " + process.exitValue());
                }
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("❌ Backup failed: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Backup failed: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

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
    private javax.swing.JPanel ContentPanel;
    private dev.ramindu.nexamarket.components.RoundButton DateTime;
    private javax.swing.JMenu Edit;
    private javax.swing.JMenu File;
    private javax.swing.JLabel Headline3;
    private javax.swing.JMenu Help;
    private javax.swing.JLabel Issue_Invoice;
    private javax.swing.JLabel Previous_Invoice;
    private javax.swing.JLabel Profile;
    private javax.swing.JLabel Sign_Out;
    private javax.swing.JMenu Tools;
    private javax.swing.JMenu View;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel timer;
    // End of variables declaration//GEN-END:variables
}
