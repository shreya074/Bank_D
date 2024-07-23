package com.dhaachuko.dao;

import com.dhaachuko.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteCustomerDAO {

    public boolean deleteCustomer(String accountNumber) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        boolean isDeleted = false;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false); // Start transaction

            // Delete related transactions
            String deleteTransactionsQuery = "DELETE FROM transactions WHERE account_number = ?";
            pstmt = connection.prepareStatement(deleteTransactionsQuery);
            pstmt.setString(1, accountNumber);
            pstmt.executeUpdate();

            // Delete customer
            String deleteCustomerQuery = "DELETE FROM customers WHERE account_number = ?";
            pstmt = connection.prepareStatement(deleteCustomerQuery);
            pstmt.setString(1, accountNumber);
            int deletedRows = pstmt.executeUpdate();

            if (deletedRows > 0) {
                connection.commit(); // Commit transaction
                isDeleted = true;
            } else {
                connection.rollback(); // Rollback transaction if deletion fails
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
        return isDeleted;
    }
}
