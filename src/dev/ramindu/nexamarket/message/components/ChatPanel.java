// ChatPanel.java
package dev.ramindu.nexamarket.message.components;

import dev.ramindu.nexamarket.message.daos.MessageDAO;
import dev.ramindu.nexamarket.message.models.Message;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ChatPanel extends JPanel {
    private int currentUserId;
    private int otherUserId;
    
    public ChatPanel(int currentUserId, int otherUserId) {
        this.currentUserId = currentUserId;
        this.otherUserId = otherUserId;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        loadMessages();
    }
    
    private void loadMessages() {
        try {
            List<Message> messages = MessageDAO.getConversation(currentUserId, otherUserId);
            for (Message message : messages) {
                addMessageBubble(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading messages", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addMessageBubble(Message message) {
        boolean isCurrentUser = message.getFromUserId() == currentUserId;
        
        JPanel bubblePanel = new JPanel();
        bubblePanel.setLayout(new BorderLayout());
        bubblePanel.setBackground(isCurrentUser ? new Color(220, 248, 198) : new Color(240, 240, 240));
        bubblePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JLabel messageLabel = new JLabel(message.getMessageText());
        bubblePanel.add(messageLabel, BorderLayout.CENTER);
        
        JLabel timeLabel = new JLabel(message.getSentTime().toString());
        timeLabel.setFont(timeLabel.getFont().deriveFont(10f));
        bubblePanel.add(timeLabel, BorderLayout.SOUTH);
        
        if (isCurrentUser) {
            bubblePanel.setAlignmentX(RIGHT_ALIGNMENT);
        } else {
            bubblePanel.setAlignmentX(LEFT_ALIGNMENT);
        }
        
        add(bubblePanel);
        add(Box.createVerticalStrut(5));
    }
    
    public void addNewMessage(Message message) {
        addMessageBubble(message);
        revalidate();
        repaint();
    }
}