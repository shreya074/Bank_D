package com.dhaachuko.dao;

import com.dhaachuko.connection.DatabaseConnection;
import com.dhaachuko.model.Customer;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerLoginDAO {

    public Customer login(String accountNumber, String password) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Customer customer = null;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT full_name, initial_balance FROM customers WHERE account_number = ? AND password = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, accountNumber);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String customerName = rs.getString("full_name");
                BigDecimal customerBalance = rs.getBigDecimal("initial_balance");

                // Create Customer object with BigDecimal for initialBalance
                customer = new Customer(accountNumber, customerName, null, null, null, null, customerBalance, null, null, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }
}
