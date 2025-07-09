package dev.ramindu.nexamarket.components;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class PieChartPanel extends JPanel {

    private Map<String, Double> data = new LinkedHashMap<>();
    private Map<String, Color> colors = new LinkedHashMap<>();

    public PieChartPanel() {
        setPreferredSize(new Dimension(400, 300)); // 🛠️ Make sure there's enough space
        setBackground(Color.WHITE);

        setData(Map.of(
            "Cash", 50.0,
            "Card", 30.0,
            "Online", 20.0
        ));
    }

    public void setData(Map<String, Double> data) {
        this.data = data;
        generateColors();
        repaint();
    }

    private void generateColors() {
        colors.clear();
        Random rand = new Random();
        for (String key : data.keySet()) {
            colors.put(key, new Color(rand.nextInt(200), rand.nextInt(200), rand.nextInt(200)));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int pieDiameter = 200;
        int x = 20;
        int y = 20;

        double total = data.values().stream().mapToDouble(Double::doubleValue).sum();
        double startAngle = 0;

        // 🍰 Draw pie chart
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            String method = entry.getKey();
            double value = entry.getValue();

            double angle = value / total * 360;

            g2.setColor(colors.get(method));
            g2.fillArc(x, y, pieDiameter, pieDiameter, (int) startAngle, (int) angle);

            startAngle += angle;
        }

        // 🏷️ Draw legend outside the pie
        int legendX = x + pieDiameter + 30; // space from pie
        int legendY = y + 10;

        g2.setFont(new Font("Arial", Font.PLAIN, 14));
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            String method = entry.getKey();
            Color color = colors.get(method);

            g2.setColor(color);
            g2.fillRect(legendX, legendY, 15, 15);

            g2.setColor(Color.BLACK);
            g2.drawString(method + " (" + entry.getValue().intValue() + "%)", legendX + 20, legendY + 12);

            legendY += 25;
        }
    }
}
