package com.dhaachuko.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dhaachuko.dao.CustomerCloseAccountDAO;

@WebServlet("/CustomerCloseAccountServlet")
public class CustomerCloseAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String customerAccountNumber = (String) session.getAttribute("customerAccountNumber");

        if (customerAccountNumber != null) {
            CustomerCloseAccountDAO dao = new CustomerCloseAccountDAO();

            try {
                boolean success = dao.closeAccount(customerAccountNumber);

                if (success) {
                    session.invalidate();
                    response.sendRedirect("customer_login.jsp");
                } else {
                    response.sendRedirect("customer_close_account.jsp?error=AccountNotFound");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("customer_close_account.jsp?error=DatabaseError");
            }
        } else {
            response.sendRedirect("customer_close_account.jsp?error=SessionError");
        }
    }
}
