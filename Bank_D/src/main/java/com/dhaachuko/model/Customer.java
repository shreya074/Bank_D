package com.dhaachuko.model;

import java.math.BigDecimal;

public class Customer {
    private String accountNumber;
    private String fullName;
    private String address;
    private String mobileNo;
    private String emailId;
    private String accountType;
    private BigDecimal initialBalance;
    private String dateOfBirth;
    private String idProof;
    private String password;

    // Default constructor
    public Customer() {}

    // Constructor with parameters
    public Customer(String accountNumber, String fullName, String address, String mobileNo, String emailId, String accountType, BigDecimal initialBalance, String dateOfBirth, String idProof, String password) {
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.address = address;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.dateOfBirth = dateOfBirth;
        this.idProof = idProof;
        this.password = password;
    }

    // Getters and setters
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public BigDecimal getInitialBalance() { return initialBalance; }
    public void setInitialBalance(BigDecimal initialBalance) { this.initialBalance = initialBalance; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getIdProof() { return idProof; }
    public void setIdProof(String idProof) { this.idProof = idProof; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
