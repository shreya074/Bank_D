package com.dhaachuko.controller;

import com.dhaachuko.dao.CustomerLoginDAO;
import com.dhaachuko.model.Customer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerLoginDAO customerLoginDAO = new CustomerLoginDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String password = request.getParameter("password");

        Customer customer = customerLoginDAO.login(accountNumber, password);

        if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("customerName", customer.getFullName());
            session.setAttribute("customerAccountNumber", customer.getAccountNumber());
            session.setAttribute("customerBalance", customer.getInitialBalance());

            response.sendRedirect("CustomerDashboardServlet");
        } else {
            response.sendRedirect("customer_login.jsp?error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
