package dev.ramindu.nexamarket.components;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class StackedProgressBar extends JPanel {

    private final java.util.List<Section> sections = new ArrayList<>();

    public StackedProgressBar() {
        setPreferredSize(new Dimension(300, 60));
        setBackground(Color.WHITE);

        // Sample data
        addSection("Lorem", new Color(106, 115, 255), 40);
        addSection("Ipsum", new Color(58, 221, 209), 20);
        addSection("Dolor", new Color(255, 204, 102), 40);
    }

    public void addSection(String label, Color color, int percent) {
        sections.add(new Section(label, color, percent));
        repaint();
    }

    public void clearSections() {
        sections.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int totalWidth = getWidth() - 40;
        int height = 20;
        int x = 20;
        int y = 10;

        int startX = x;
        int gap = 2; // spacing between segments

        for (int i = 0; i < sections.size(); i++) {
            Section section = sections.get(i);
            int barWidth = (int) (totalWidth * (section.percent / 100.0)) - gap;

            g2.setColor(section.color);

            // First section - round left only
            if (i == 0) {
                g2.fillRoundRect(startX, y, barWidth, height, 20, 20);
                g2.fillRect(startX + barWidth / 2, y, barWidth / 2, height);
            } // Last section - round right only
            else if (i == sections.size() - 1) {
                g2.fillRoundRect(startX, y, barWidth, height, 20, 20);
                g2.fillRect(startX, y, barWidth / 2, height);
            } // Middle sections - flat
            else {
                g2.fillRect(startX, y, barWidth, height);
            }

            startX += barWidth + gap;
        }

        // ✅ Wrapped Legends
        int legendStartY = y + height + 15;
        int legendX = x;
        int legendY = legendStartY;
        int maxWidth = getWidth() - 20;
        int lineHeight = 20;

        for (Section section : sections) {
            String label = section.label;
            int labelWidth = g2.getFontMetrics().stringWidth(label) + 30;

            if (legendX + labelWidth > maxWidth) {
                // Move to next line
                legendX = x;
                legendY += lineHeight;
            }

            // Draw color box
            g2.setColor(section.color);
            g2.fillRect(legendX, legendY, 15, 15);

            // Draw label
            g2.setColor(Color.DARK_GRAY);
            g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
            g2.drawString(label, legendX + 20, legendY + 12);

            legendX += labelWidth + 15;
        }

        g2.dispose();
    }

    private static class Section {

        String label;
        Color color;
        int percent;

        Section(String label, Color color, int percent) {
            this.label = label;
            this.color = color;
            this.percent = percent;
        }
    }
}
