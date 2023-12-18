package com.bankapp;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected int accountNumber;
    protected double balance;
    protected int customerId;
    protected List<Transaction> transactions;

    private static int nextAccountNumber = 1000; // Static counter for generating unique account numbers

    public Account(int customerId) {
        this.customerId = customerId;
        this.accountNumber = nextAccountNumber++;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public abstract void withdraw(double amount);

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            transactions.add(new Transaction("Credit", amount));
        } else {
            // Handle invalid deposit amount
            System.out.println("Invalid deposit amount.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    // Additional common methods or utilities can be added here
    public String printStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("Account Statement for Account Number: ").append(accountNumber).append("\n");
        for (Transaction transaction : transactions) {
            statement.append(transaction).append("\n");
        }
        return statement.toString();
    }

    public void updateAccountDetails(String newAddress) {
        // Assuming the account has an address field
        // this.address = newAddress;
    }

    public boolean canCloseAccount() {
        return balance == 0;
    }

}
