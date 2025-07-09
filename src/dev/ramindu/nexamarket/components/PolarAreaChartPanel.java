/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.ramindu.nexamarket.components;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PolarAreaChartPanel extends JPanel {

    static class PolarItem {
        String label;
        double value;
        Color color;

        public PolarItem(String label, double value, Color color) {
            this.label = label;
            this.value = value;
            this.color = color;
        }
    }

    private final List<PolarItem> items = new ArrayList<>();

    public PolarAreaChartPanel() {
        // Example data
        items.add(new PolarItem("Rice", 60, new Color(255, 99, 132)));
        items.add(new PolarItem("Dhal", 30, new Color(54, 162, 235)));
        items.add(new PolarItem("Sugar", 45, new Color(255, 206, 86)));
        items.add(new PolarItem("Flour", 80, new Color(75, 192, 192)));
    }

    // ➤ GUI Builder එකට friendly preferred size එක
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(344, 260); // Match your panel size
    }

    public void addItem(String label, double value, Color color) {
        items.add(new PolarItem(label, value, color));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPolarChart((Graphics2D) g);
    }

    private void drawPolarChart(Graphics2D g2) {
        int width = getWidth();
        int height = getHeight();

        int centerX = width / 3;
        int centerY = height / 2;
        int maxRadius = Math.min(width, height) / 3;

        double maxValue = items.stream().mapToDouble(i -> i.value).max().orElse(1);
        double angleStep = 360.0 / items.size();

        // Draw sectors
        for (int i = 0; i < items.size(); i++) {
            PolarItem item = items.get(i);

            double radius = (item.value / maxValue) * maxRadius;
            double startAngle = i * angleStep;

            g2.setColor(item.color);
            g2.fillArc(centerX - (int)radius, centerY - (int)radius,
                       (int)radius * 2, (int)radius * 2,
                       (int)startAngle, (int)angleStep);
        }

        // Draw legend (right side)
        int legendX = (int)(width * 0.65);
        int legendY = 20;
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 12));

        for (PolarItem item : items) {
            g2.setColor(item.color);
            g2.fillRect(legendX, legendY, 15, 15);
            g2.setColor(Color.BLACK);
            g2.drawString(item.label + " (" + (int)item.value + "%)", legendX + 20, legendY + 12);
            legendY += 22;
        }
    }
}
