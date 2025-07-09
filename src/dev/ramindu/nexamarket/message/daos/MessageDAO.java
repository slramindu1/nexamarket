// MessageDAO.java
package dev.ramindu.nexamarket.message.daos;

import dev.ramindu.nexamarket.message.models.Message;
import dev.ramindu.nexamarket.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    public static void sendMessage(int fromUserId, int toUserId, String messageText) throws SQLException {
        String sql = "INSERT INTO message (from_user_id, to_user_id, message_text) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, fromUserId);
            stmt.setInt(2, toUserId);
            stmt.setString(3, messageText);
            stmt.executeUpdate();
        }
    }
    
    public static List<Message> getConversation(int user1Id, int user2Id) throws SQLException {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM message WHERE (from_user_id = ? AND to_user_id = ?) " +
                     "OR (from_user_id = ? AND to_user_id = ?) ORDER BY sent_time ASC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, user1Id);
            stmt.setInt(2, user2Id);
            stmt.setInt(3, user2Id);
            stmt.setInt(4, user1Id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Message message = new Message(
                        rs.getInt("id"),
                        rs.getInt("from_user_id"),
                        rs.getInt("to_user_id"),
                        rs.getString("message_text"),
                        rs.getTimestamp("sent_time")
                    );
                    messages.add(message);
                }
            }
        }
        return messages;
    }
}