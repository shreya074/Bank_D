package com.dhaachuko.dao;

import com.dhaachuko.connection.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CustomerWithdrawDAO {

    public boolean withdrawAmount(String accountNumber, BigDecimal amount) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false); // Start transaction

            // Check current balance
            String checkBalanceQuery = "SELECT initial_balance FROM customers WHERE account_number = ?";
            pstmt = connection.prepareStatement(checkBalanceQuery);
            pstmt.setString(1, accountNumber);
            var rs = pstmt.executeQuery();

            if (rs.next()) {
                BigDecimal currentBalance = rs.getBigDecimal("initial_balance");
                if (currentBalance.compareTo(amount) >= 0) {
                    // Update customer balance
                    String updateQuery = "UPDATE customers SET initial_balance = initial_balance - ? WHERE account_number = ?";
                    pstmt.close(); // Close previous statement
                    pstmt = connection.prepareStatement(updateQuery);
                    pstmt.setBigDecimal(1, amount);
                    pstmt.setString(2, accountNumber);
                    int updatedRows = pstmt.executeUpdate();

                    if (updatedRows > 0) {
                        // Insert transaction record
                        String insertTransactionQuery = "INSERT INTO transactions (account_number, transaction_type, amount, transaction_time) VALUES (?, ?, ?, ?)";
                        pstmt.close(); // Close previous statement
                        pstmt = connection.prepareStatement(insertTransactionQuery);
                        pstmt.setString(1, accountNumber);
                        pstmt.setString(2, "Withdrawal");
                        pstmt.setBigDecimal(3, amount);
                        pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                        pstmt.executeUpdate();

                        connection.commit(); // Commit transaction
                        success = true;
                    } else {
                        connection.rollback(); // Rollback transaction if update fails
                    }
                } else {
                    // Insufficient funds
                    connection.rollback(); // Rollback transaction
                }
            } else {
                // Account not found
                connection.rollback(); // Rollback transaction
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback(); // Rollback transaction on exception
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }

    public BigDecimal getCurrentBalance(String accountNumber) {
        BigDecimal balance = BigDecimal.ZERO;
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT initial_balance FROM customers WHERE account_number = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, accountNumber);
            var rs = pstmt.executeQuery();

            if (rs.next()) {
                balance = rs.getBigDecimal("initial_balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return balance;
    }
}
