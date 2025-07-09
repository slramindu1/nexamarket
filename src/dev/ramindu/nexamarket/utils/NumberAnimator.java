package dev.ramindu.nexamarket.utils;

import javax.swing.*;

public class NumberAnimator {
    private int current = 0;
    private int target = 0;
    private Timer timer;

    public NumberAnimator(JLabel label, int to) {
        target = to;

        int duration = 8000; // 8 seconds in milliseconds
        int interval = 10;   // update every 10ms
        int steps = duration / interval;
        int stepAmount = Math.max(1, target / steps);

        timer = new Timer(interval, e -> {
            if (current < target) {
                current += stepAmount;
                if (current > target) current = target;
                label.setText("Rs. " + String.format("%,d", current));
            } else {
                label.setText("Rs. " + String.format("%,d", target));
                ((Timer) e.getSource()).stop();
            }
        });
    }

    public void start() {
        current = 0;
        timer.start();
    }
}
