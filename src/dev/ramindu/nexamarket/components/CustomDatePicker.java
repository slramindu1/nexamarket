package dev.ramindu.nexamarket.components;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDatePicker extends JPanel {

    public Date getDate() {
        return dateChooser.getDate();
    }

    public void setDate(Date date) {
        dateChooser.setDate(date);
    }

    private JDateChooser dateChooser;

    public CustomDatePicker() {
        setLayout(new BorderLayout());

        // 🔹 Create JDateChooser with format
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");

        // 🔹 Access the date text field
        JTextField dateTextField = ((JTextField) dateChooser.getDateEditor().getUiComponent());

        // ✅ Add only left padding to the text field
        dateTextField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // top, left, bottom, right

        // Optional: clean flat look
        dateTextField.setOpaque(true);
        dateTextField.setBackground(Color.WHITE);
        dateTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        try {
            // 🔹 Load custom calendar icon
            URL url = getClass().getResource("/dev/ramindu/nexamarket/img/calandar-icon.png");
            if (url != null) {
                ImageIcon icon = new ImageIcon(url);
                Image img = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
                icon = new ImageIcon(img);

                // ✅ Set icon to JDateChooser
                dateChooser.setIcon(icon);

                // ✅ Find and pad the calendar button
                for (Component comp : dateChooser.getComponents()) {
                    if (comp instanceof JButton) {
                        JButton calendarButton = (JButton) comp;
                        calendarButton.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8)); // left/right padding
                        break;
                    }
                }

            } else {
                System.err.println("❌ Calendar icon not found at specified path.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(dateChooser, BorderLayout.CENTER);
    }

    // 🔹 Public method to get selected date as String
    public String getSelectedDate() {
        Date date = dateChooser.getDate();
        if (date != null) {
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        }
        return null;
    }

    // 🔹 Set a date programmatically
    public void setSelectedDate(Date date) {
        dateChooser.setDate(date);
    }

    // 🔹 Expose full JDateChooser if needed
    public JDateChooser getDateChooser() {
        return dateChooser;
    }
}
