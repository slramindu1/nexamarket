
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.ramindu.nexamarket.components;

import java.awt.*;
import javax.swing.*;

public class CircularChart extends JPanel {
    private int progress = 0;
    private Color progressColor = Color.ORANGE;
    private String labelText = "0K";

    public CircularChart() {
        setPreferredSize(new Dimension(100, 100));
        setOpaque(false);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        repaint();
    }

    public void setProgressColor(Color color) {
        this.progressColor = color;
        repaint();
    }

    public void setLabelText(String text) {
        this.labelText = text;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background circle
        g2.setColor(new Color(220, 220, 220));
        g2.setStroke(new BasicStroke(10f));
        g2.drawArc(10, 10, size - 20, size - 20, 0, 360);

        // Progress arc
        g2.setColor(progressColor);
        g2.drawArc(10, 10, size - 20, size - 20, 90, -(int) (360 * (progress / 100.0)));

        // Text
        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("SansSerif", Font.BOLD, 18));
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(labelText);
        int textHeight = fm.getAscent();
        g2.drawString(labelText, width / 2 - textWidth / 2, height / 2 + textHeight / 4);
    }
}

