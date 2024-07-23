package com.dhaachuko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.dhaachuko.connection.DatabaseConnection;

public class PasswordDAO {

    public boolean verifyCurrentPassword(String accountNumber, String currentPassword) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isVerified = false;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT password FROM customers WHERE account_number = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, accountNumber);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                isVerified = storedPassword.equals(currentPassword);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return isVerified;
    }

    public boolean updatePassword(String accountNumber, String newPassword) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        boolean isUpdated = false;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "UPDATE customers SET password = ? WHERE account_number = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, newPassword); // Ensure to hash this password in production
            pstmt.setString(2, accountNumber);
            int rowsAffected = pstmt.executeUpdate();
            isUpdated = rowsAffected > 0;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return isUpdated;
    }
}
