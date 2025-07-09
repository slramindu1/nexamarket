/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.ramindu.nexamarket.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StockLevelMeterPanel extends JPanel {

    static class StockItem {
        String name;
        int percentage;

        public StockItem(String name, int percentage) {
            this.name = name;
            this.percentage = percentage;
        }
    }

    private final List<StockItem> items = new ArrayList<>();

    public StockLevelMeterPanel() {
        // ➕ 7 Products now!
        items.add(new StockItem("Rice", 80));
        items.add(new StockItem("Dhal", 55));
        items.add(new StockItem("Milk", 25));
        items.add(new StockItem("Sugar", 10));
        items.add(new StockItem("Flour", 100));
        items.add(new StockItem("Soap", 60));
        items.add(new StockItem("Oil", 45));
    }

    public void addItem(String name, int percentage) {
        items.add(new StockItem(name, percentage));
        repaint();
    }

    public void clearItems() {
        items.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawStockBars((Graphics2D) g);
    }

    private void drawStockBars(Graphics2D g2) {
    int width = getWidth();
    int height = getHeight();

    // 📍 Draw Title
    String title = "Available Stock";
    g2.setColor(Color.BLACK);
    g2.setFont(new Font("SansSerif", Font.BOLD, 14));
    FontMetrics fm = g2.getFontMetrics();
    int titleHeight = fm.getHeight();
    int titleX = (width - fm.stringWidth(title)) / 2;
    int titleY = titleHeight;
    g2.drawString(title, titleX, titleY);

    // 🔢 Calculate sizes
    int itemCount = items.size();
    int spacing = 10;
    int totalSpacing = spacing * (itemCount + 1);
    int availableWidth = width - totalSpacing;
    int barWidth = availableWidth / itemCount;

    // 📏 Reserve space for label height
    int labelHeight = g2.getFontMetrics().getHeight();
    
    // ⬇️ Compute bar height to avoid bottom padding
    int topY = titleY + 10;
    int barHeight = height - topY - labelHeight - 5; // 5px gap from label
    int baseY = topY;

    g2.setFont(new Font("SansSerif", Font.PLAIN, 12));

    for (int i = 0; i < items.size(); i++) {
        StockItem item = items.get(i);
        int x = spacing + i * (barWidth + spacing);

        // Background
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(x, baseY, barWidth, barHeight);

        // Fill
        int fillHeight = (int)((item.percentage / 100.0) * barHeight);
        g2.setColor(getBarColor(item.percentage));
        g2.fillRect(x, baseY + barHeight - fillHeight, barWidth, fillHeight);

        // Outline
        g2.setColor(Color.BLACK);
        g2.drawRect(x, baseY, barWidth, barHeight);

        // Label (directly below bar)
        drawCenteredText(g2, item.name, x, baseY + barHeight + labelHeight - 4, barWidth);
    }
}


    private void drawCenteredText(Graphics2D g2, String text, int x, int y, int width) {
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int drawX = x + (width - textWidth) / 2;
        g2.setColor(Color.BLACK);
        g2.drawString(text, drawX, y);
    }

    private Color getBarColor(int percentage) {
        if (percentage < 30) return new Color(255, 99, 132);  // Red
        if (percentage < 70) return new Color(255, 206, 86);  // Yellow
        return new Color(75, 192, 192);                      // Green
    }
}
