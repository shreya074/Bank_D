package com.dhaachuko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date; // Import Date
import com.dhaachuko.connection.DatabaseConnection;
import com.dhaachuko.model.Customer;

public class ViewCustomerDAO {

    /**
     * Retrieves all customers from the database.
     *
     * @return List of Customer objects
     * @throws SQLException if a database access error occurs
     */
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM customers";
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();

            // Date formatter
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setAccountNumber(rs.getString("account_number"));
                customer.setFullName(rs.getString("full_name"));
                customer.setAddress(rs.getString("address"));
                customer.setMobileNo(rs.getString("mobile_no"));
                customer.setEmailId(rs.getString("email_id"));
                customer.setAccountType(rs.getString("account_type"));
                customer.setInitialBalance(rs.getBigDecimal("initial_balance"));

                // Convert Date to String
                Date dateOfBirth = rs.getDate("date_of_birth");
                String dateOfBirthString = dateOfBirth != null ? sdf.format(dateOfBirth) : null;
                customer.setDateOfBirth(dateOfBirthString);

                customer.setIdProof(rs.getString("id_proof"));

                customers.add(customer);
            }
        } finally {
            // Close resources
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (connection != null) connection.close();
        }
        return customers;
    }
}
