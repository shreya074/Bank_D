package com.dhaachuko.controller;

import com.dhaachuko.service.TransactionService;
import com.dhaachuko.model.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/CustomerTransactionsServlet")
public class CustomerTransactionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final TransactionService transactionService = new TransactionService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("accountNumber");

        if (accountNumber == null) {
            // Redirect to login if account number is not found in session
            response.sendRedirect("customer_login.jsp");
            return;
        }

        String action = request.getParameter("action");

        if ("pdf".equals(action)) {
            // Fetch last 10 transactions and generate PDF
            List<Transaction> transactions = transactionService.getLast10Transactions(accountNumber);
            generatePDF(transactions, response);
        } else {
            // Fetch all transactions and display on frontend (not shown in the example)
            List<Transaction> transactions = transactionService.getAllTransactions(accountNumber);
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("customer_transactions.jsp").forward(request, response);
        }
    }

    private void generatePDF(List<Transaction> transactions, HttpServletResponse response) throws IOException {
        try {
            // Set response headers for PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"transaction_history.pdf\"");

            // Create PDF document
            OutputStream out = response.getOutputStream();

            // Write PDF content
            out.write("Transaction ID | Date | Transaction Type | Amount\n".getBytes());
            for (Transaction transaction : transactions) {
                out.write((transaction.getTransactionId() + " | "
                        + transaction.getTransactionTime() + " | "
                        + transaction.getTransactionType() + " | "
                        + transaction.getAmount() + "\n").getBytes());
            }

            // Close output stream
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error generating PDF", e);
        }
    }
}
