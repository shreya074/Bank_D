package com.dhaachuko.service;

import com.dhaachuko.dao.TransactionDAO;
import com.dhaachuko.model.Transaction;

import java.util.List;

public class TransactionService {
    private final TransactionDAO transactionDAO;

    public TransactionService() {
        this.transactionDAO = new TransactionDAO();
    }

    public List<Transaction> getAllTransactions(String accountNumber) {
        return transactionDAO.getAllTransactions(accountNumber);
    }

    public List<Transaction> getLast10Transactions(String accountNumber) {
        return transactionDAO.getLast10Transactions(accountNumber);
    }
}
