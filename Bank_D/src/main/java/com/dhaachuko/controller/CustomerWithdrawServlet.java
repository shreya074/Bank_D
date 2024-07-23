package com.dhaachuko.controller;

import com.dhaachuko.dao.CustomerWithdrawDAO;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CustomerWithdrawServlet")
public class CustomerWithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String customerAccountNumber = (String) session.getAttribute("customerAccountNumber");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));

        CustomerWithdrawDAO withdrawDAO = new CustomerWithdrawDAO();

        boolean withdrawSuccess = withdrawDAO.withdrawAmount(customerAccountNumber, amount);

        if (withdrawSuccess) {
            // Update session with new balance
            BigDecimal updatedBalance = withdrawDAO.getCurrentBalance(customerAccountNumber);
            session.setAttribute("customerBalance", updatedBalance);
            response.sendRedirect("customer_dashboard.jsp?success=WithdrawSuccessful");
        } else {
            // Handle withdrawal failure
            response.sendRedirect("customer_dashboard.jsp?error=WithdrawFailed");
        }
    }
}
