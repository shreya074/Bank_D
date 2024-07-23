package com.dhaachuko.dao;

import com.dhaachuko.connection.DatabaseConnection;
import com.dhaachuko.model.Transaction;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // Method to get all transactions for a given account number
    public List<Transaction> getAllTransactions(String accountNumber) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE account_number = ? ORDER BY transaction_time DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int transactionId = rs.getInt("id");
                LocalDateTime transactionTime = rs.getTimestamp("transaction_time").toLocalDateTime();
                String transactionType = rs.getString("transaction_type");
                BigDecimal amount = rs.getBigDecimal("amount");

                transactions.add(new Transaction(transactionId, transactionTime, transactionType, amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    // Method to get the last 10 transactions for a given account number
    public List<Transaction> getLast10Transactions(String accountNumber) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE account_number = ? ORDER BY transaction_time DESC LIMIT 10";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int transactionId = rs.getInt("id");
                LocalDateTime transactionTime = rs.getTimestamp("transaction_time").toLocalDateTime();
                String transactionType = rs.getString("transaction_type");
                BigDecimal amount = rs.getBigDecimal("amount");

                transactions.add(new Transaction(transactionId, transactionTime, transactionType, amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
