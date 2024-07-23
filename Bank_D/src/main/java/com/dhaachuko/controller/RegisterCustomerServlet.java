package com.dhaachuko.controller;

import com.dhaachuko.dao.CustomerRegistrationDAO;
import com.dhaachuko.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/RegisterCustomerServlet")
public class RegisterCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String emailId = request.getParameter("emailId");
        String accountType = request.getParameter("accountType");
        
        // Convert initialBalance to BigDecimal
        BigDecimal initialBalance = new BigDecimal(request.getParameter("initialBalance"));
        
        String dateOfBirth = request.getParameter("dateOfBirth");
        String idProof = request.getParameter("idProof");

        CustomerRegistrationDAO customerDAO = new CustomerRegistrationDAO();
        String password = customerDAO.generatePassword();
        String accountNumber = customerDAO.generateAccountNumber();

        // Create Customer object using BigDecimal for initialBalance
        Customer customer = new Customer(accountNumber, fullName, address, mobileNo, emailId, accountType, initialBalance, dateOfBirth, idProof, password);

        boolean isRegistered = customerDAO.registerCustomer(customer);
        if (isRegistered) {
            request.setAttribute("message", "Customer registered successfully! Account Number: " + accountNumber + " Password: " + password);
        } else {
            request.setAttribute("message", "An error occurred while registering the customer.");
        }
        request.getRequestDispatcher("register_customer.jsp").forward(request, response);
    }
}
