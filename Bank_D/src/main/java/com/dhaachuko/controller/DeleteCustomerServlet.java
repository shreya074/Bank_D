package com.dhaachuko.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhaachuko.dao.DeleteCustomerDAO;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeleteCustomerDAO deleteCustomerDAO = new DeleteCustomerDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");

        if (accountNumber != null && !accountNumber.isEmpty()) {
            boolean isDeleted = deleteCustomerDAO.deleteCustomer(accountNumber);

            if (isDeleted) {
                response.sendRedirect("admin_dashboard.jsp?message=CustomerDeleted");
            } else {
                response.sendRedirect("admin_dashboard.jsp?error=CustomerNotFound");
            }

        } else {
            response.sendRedirect("admin_dashboard.jsp?error=MissingAccountNumber");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
