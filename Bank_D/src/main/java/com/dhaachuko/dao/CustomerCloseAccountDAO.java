package com.dhaachuko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.dhaachuko.connection.DatabaseConnection;

public class CustomerCloseAccountDAO {

    public boolean closeAccount(String accountNumber) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        boolean success = false;

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
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                connection.commit(); // Commit transaction
                success = true;
            } else {
                connection.rollback(); // Rollback transaction on failure
            }
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback(); // Rollback transaction on exception
            }
            throw e; // Rethrow exception to be handled by the caller
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return success;
    }
}
