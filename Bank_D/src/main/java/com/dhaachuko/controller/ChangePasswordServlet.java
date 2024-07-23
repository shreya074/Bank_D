package com.dhaachuko.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dhaachuko.dao.PasswordDAO;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Check if new password and confirm password match
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("message", "New passwords do not match.");
            request.getRequestDispatcher("change_password.jsp").forward(request, response);
            return;
        }

        PasswordDAO passwordDAO = new PasswordDAO();

        try {
            // Verify current password
            boolean isCurrentPasswordValid = passwordDAO.verifyCurrentPassword(accountNumber, currentPassword);

            if (isCurrentPasswordValid) {
                // Update password
                boolean isPasswordUpdated = passwordDAO.updatePassword(accountNumber, newPassword);

                if (isPasswordUpdated) {
                    request.setAttribute("message", "Password changed successfully.");
                } else {
                    request.setAttribute("message", "Failed to change password.");
                }
            } else {
                request.setAttribute("message", "Current password is incorrect.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred while updating the password.");
        }

        request.getRequestDispatcher("change_password.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported.");
    }
}
