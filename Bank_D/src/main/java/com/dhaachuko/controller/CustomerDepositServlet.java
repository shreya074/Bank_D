package com.dhaachuko.controller;

import com.dhaachuko.dao.CustomerDepositDAO;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CustomerDepositServlet")
public class CustomerDepositServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String customerAccountNumber = (String) session.getAttribute("customerAccountNumber");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));

        CustomerDepositDAO depositDAO = new CustomerDepositDAO();

        boolean depositSuccess = depositDAO.depositAmount(customerAccountNumber, amount);

        if (depositSuccess) {
            // Update session with new balance
            BigDecimal updatedBalance = depositDAO.getCurrentBalance(customerAccountNumber);
            session.setAttribute("customerBalance", updatedBalance);
            response.sendRedirect("customer_dashboard.jsp?success=DepositSuccessful");
        } else {
            // Handle deposit failure
            response.sendRedirect("customer_dashboard.jsp?error=DepositFailed");
        }
    }
}
