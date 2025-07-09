/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.ramindu.nexamarket.components;

import java.awt.*;
import javax.swing.*;
import com.formdev.flatlaf.FlatClientProperties;

public class GradientCardPanel2 extends JPanel {

    public GradientCardPanel2() {
        setOpaque(false);

        // Add FlatLaf rounded corner style
        putClientProperty(FlatClientProperties.STYLE, "arc:50");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Gradient background
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(0, 210, 255), // Teal Cyan
                getWidth(), getHeight(), new Color(0, 255, 135) // Lime Green
        );

        g2d.setPaint(gp);
        g2d.fillRoundRect(0, 0, width, height, 50, 50); // same arc as style

        // Soft circles
        g2d.setColor(new Color(255, 255, 255, 40));
        g2d.fillOval(width - 140, -20, 180, 180);   // Top right
        g2d.fillOval(width - 160, height - 120, 200, 200); // Bottom right

        g2d.dispose();
    }
}
