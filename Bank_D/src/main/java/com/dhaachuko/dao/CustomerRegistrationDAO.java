package com.dhaachuko.dao;

import com.dhaachuko.model.Customer;
import com.dhaachuko.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.security.SecureRandom;
import java.util.Random;

public class CustomerRegistrationDAO {

    public String generateAccountNumber() {
        Random random = new SecureRandom();
        StringBuilder accountNumber = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }

    public String generatePassword() {
        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder(8);
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        for (int i = 0; i < 8; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    public boolean registerCustomer(Customer customer) {
        String sql = "INSERT INTO customers (account_number, full_name, address, mobile_no, email_id, account_type, initial_balance, date_of_birth, id_proof, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getAccountNumber());
            statement.setString(2, customer.getFullName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getMobileNo());
            statement.setString(5, customer.getEmailId());
            statement.setString(6, customer.getAccountType());
            statement.setBigDecimal(7, customer.getInitialBalance()); // Use setBigDecimal for BigDecimal
            statement.setString(8, customer.getDateOfBirth().toString()); // Convert LocalDate to String
            statement.setString(9, customer.getIdProof());
            statement.setString(10, customer.getPassword());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
