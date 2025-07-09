package dev.ramindu.nexamarket.components;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class CircularProgressBar extends JPanel {

    private int progress = 75; // Default 75%

    public CircularProgressBar() {
        setPreferredSize(new Dimension(200, 200));
        setBackground(Color.WHITE);
    }

    public void setProgress(int value) {
        this.progress = value;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Smooth edges
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int strokeWidth = 20;
        int size = Math.min(getWidth(), getHeight()) - strokeWidth;
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        g2.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // 🔘 Draw background arc (gray) FIRST – remaining area
        if (progress < 100) {
            Shape remainingArc = new Arc2D.Double(x, y, size, size,
                    90 + (progress * 3.6f), 360 - (progress * 3.6f), Arc2D.OPEN);

            g2.setColor(Color.LIGHT_GRAY);
            g2.draw(remainingArc);
        }

        // ✅ Draw progress arc – CLOCKWISE (left to right)
        Shape progressArc = new Arc2D.Double(x, y, size, size, 90, progress * 3.6, Arc2D.OPEN);
        g2.setColor(Color.decode("#00CC66")); // Green
        g2.draw(progressArc);

        // 🖋️ Draw center text
        String text = progress + "%";
        g2.setFont(new Font("Arial", Font.BOLD, 28));
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 4);
    }
}
