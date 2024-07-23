package com.dhaachuko.controller;

import com.dhaachuko.dao.CustomerDashboardDAO;
import com.dhaachuko.model.Customer;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CustomerDashboardServlet")
public class CustomerDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDashboardDAO customerDashboardDAO = new CustomerDashboardDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String customerAccountNumber = (String) session.getAttribute("customerAccountNumber");

        if (customerAccountNumber != null && !customerAccountNumber.isEmpty()) {
            Customer customer = customerDashboardDAO.getCustomerDetails(customerAccountNumber);
            if (customer != null) {
                BigDecimal currentBalance = customer.getInitialBalance();

                // Update balance in the database to ensure it's current
                customerDashboardDAO.updateCustomerBalance(customerAccountNumber, currentBalance);

                session.setAttribute("customerName", customer.getFullName());
                session.setAttribute("customerBalance", currentBalance);

                response.sendRedirect("customer_dashboard.jsp");
            } else {
                response.sendRedirect("customer_login.jsp?error=true");
            }
        } else {
            response.sendRedirect("customer_login.jsp?error=true");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
