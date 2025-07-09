package dev.ramindu.nexamarket.components;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.TextStyle;
import java.util.*;

public class ModernCalendar extends JPanel {

    private LocalDate currentDate;

    public ModernCalendar() {
        this.currentDate = LocalDate.now();
        setPreferredSize(new Dimension(300, 300));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int padding = 20;
        int dayBoxSize = (width - padding * 2) / 7;

        // Draw Month Name with arrows
        String month = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        g2.setColor(new Color(60, 60, 60));
        g2.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2.drawString("< " + month + " >", padding + 50, padding + 10);

        // Draw days of week
        g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
        DayOfWeek[] days = DayOfWeek.values();
        for (int i = 0; i < 7; i++) {
            String day = days[(i + 6) % 7].getDisplayName(TextStyle.SHORT, Locale.ENGLISH); // Start from Sunday
            g2.drawString(day, padding + i * dayBoxSize + 10, padding + 30);
        }

        // Draw day numbers
        LocalDate firstDay = currentDate.withDayOfMonth(1);
        int dayOfWeek = firstDay.getDayOfWeek().getValue(); // Monday = 1
        int offset = (dayOfWeek % 7); // Adjust so Sunday = 0

        int x = padding;
        int y = padding + 50;
        int daysInMonth = currentDate.lengthOfMonth();

        for (int i = 1; i <= daysInMonth; i++) {
            int col = (offset + i - 1) % 7;
            int row = (offset + i - 1) / 7;

            int boxX = padding + col * dayBoxSize;
            int boxY = y + row * dayBoxSize;

            // Draw circle
            g2.setColor(new Color(220, 255, 255));
            g2.fillOval(boxX + 5, boxY + 5, dayBoxSize - 10, dayBoxSize - 10);

            // Draw number
            g2.setColor(Color.BLACK);
            g2.drawString(String.valueOf(i), boxX + dayBoxSize / 2 - 5, boxY + dayBoxSize / 2 + 5);
        }
    }
}
