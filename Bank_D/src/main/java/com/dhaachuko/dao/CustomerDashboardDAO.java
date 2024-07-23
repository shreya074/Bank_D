package com.dhaachuko.dao;

import com.dhaachuko.connection.DatabaseConnection;
import com.dhaachuko.model.Customer;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDashboardDAO {

    public Customer getCustomerDetails(String accountNumber) {
        Customer customer = null;
        String query = "SELECT full_name, initial_balance FROM customers WHERE account_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, accountNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String fullName = rs.getString("full_name");
                    BigDecimal initialBalance = rs.getBigDecimal("initial_balance");

                    customer = new Customer(accountNumber, fullName, "", "", "", "", initialBalance, "", "", "");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }
    
    public void updateCustomerBalance(String accountNumber, BigDecimal newBalance) {
        String query = "UPDATE customers SET initial_balance = ? WHERE account_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setBigDecimal(1, newBalance);
            pstmt.setString(2, accountNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
