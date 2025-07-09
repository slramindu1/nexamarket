// UserDAO.java
package dev.ramindu.nexamarket.message.daos;

import dev.ramindu.nexamarket.message.models.User;
import dev.ramindu.nexamarket.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static User getUserById(int userId) throws SQLException {
        String sql = "SELECT id, username, `First Name`, `Last Name`, email FROM user WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("First Name"),
                        rs.getString("Last Name"),
                        rs.getString("email")
                    );
                }
            }
        }
        return null;
    }
    
    public static List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, username, `First Name`, `Last Name`, email FROM user";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("First Name"),
                    rs.getString("Last Name"),
                    rs.getString("email")
                );
                users.add(user);
            }
        }
        return users;
    }
}