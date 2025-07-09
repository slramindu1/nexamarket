package dev.ramindu.nexamarket.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TimelineChartPanel extends JPanel {

    private final List<TimelineItem> items = new ArrayList<>();

    public TimelineChartPanel() {
        setPreferredSize(new Dimension(600, 150));
        setBackground(Color.WHITE);

        // Sample data
        addItem("Lorem", Position.BOTTOM, new Color(255, 204, 102));
        addItem("Ipsum", Position.TOP, new Color(58, 221, 209));
        addItem("Dolor", Position.BOTTOM, new Color(255, 204, 102));
        addItem("Sit", Position.TOP, new Color(58, 221, 209));
        addItem("Amet", Position.BOTTOM, new Color(255, 204, 102));
        addItem("Lorem", Position.TOP, new Color(58, 221, 209));
    }

    public void addItem(String label, Position position, Color bubbleColor) {
        items.add(new TimelineItem(label, position, bubbleColor));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int lineY = height / 2;

        // Draw base line
        g2.setColor(new Color(106, 115, 255)); // blue line
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(20, lineY, width - 20, lineY);

        // Circle spacing
        int circleSpacing = (width - 40) / (items.size() - 1);
        int circleRadius = 12;

        for (int i = 0; i < items.size(); i++) {
            TimelineItem item = items.get(i);
            int centerX = 20 + i * circleSpacing;

            // Draw circle
            g2.setColor(item.bubbleColor);
            g2.fillOval(centerX - circleRadius / 2, lineY - circleRadius / 2, circleRadius, circleRadius);

            // Draw label bubble
            int bubbleWidth = g2.getFontMetrics().stringWidth(item.label) + 20;
            int bubbleHeight = 25;
            int bubbleX = centerX - bubbleWidth / 2;
            int bubbleY = (item.position == Position.TOP) ? (lineY - 40) : (lineY + 15);

            // Bubble shape
            Polygon bubble = new Polygon();
            bubble.addPoint(bubbleX, bubbleY);
            bubble.addPoint(bubbleX + bubbleWidth, bubbleY);
            bubble.addPoint(bubbleX + bubbleWidth, bubbleY + bubbleHeight - 5);
            bubble.addPoint(centerX + 5, bubbleY + bubbleHeight - 5);
            bubble.addPoint(centerX, bubbleY + bubbleHeight);
            bubble.addPoint(centerX - 5, bubbleY + bubbleHeight - 5);
            bubble.addPoint(bubbleX, bubbleY + bubbleHeight - 5);

            g2.setColor(item.bubbleColor);
            g2.fillPolygon(bubble);

            // Draw label text
            g2.setColor(Color.DARK_GRAY);
            g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
            g2.drawString(item.label, bubbleX + 10, bubbleY + 17);
        }

        g2.dispose();
    }

    private static class TimelineItem {

        String label;
        Position position;
        Color bubbleColor;

        public TimelineItem(String label, Position position, Color bubbleColor) {
            this.label = label;
            this.position = position;
            this.bubbleColor = bubbleColor;
        }
    }

    public enum Position {
        TOP, BOTTOM
    }

    public void clearItems() {
        items.clear();
        repaint();
    }
}
