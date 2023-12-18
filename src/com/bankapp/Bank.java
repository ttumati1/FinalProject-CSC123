package com.bankapp;

import java.util.*;

public class Bank {
    private Map<Integer, Customer> customers;
    private Map<Integer, Account> accounts;
    private int nextCustomerId;
    private int nextAccountId;

    public Bank() {
        customers = new HashMap<>();
        accounts = new HashMap<>();
        nextCustomerId = 1;
        nextAccountId = 1;
    }

    public int addCustomer(String name, Date dob, String address) {
        int customerId = nextCustomerId++;
        Customer newCustomer = new Customer(customerId, name, dob, address);
        customers.put(customerId, newCustomer);
        return customerId;
    }

    public int openAccount(int customerId, String accountType, double initialDeposit) {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return -1;
        }

        Account newAccount;
        int accountId = nextAccountId++;
        if ("checking".equalsIgnoreCase(accountType)) {
            newAccount = new CheckingAccount(customerId, initialDeposit); // Assuming initial deposit is the overdraft
                                                                          // limit
        } else if ("savings".equalsIgnoreCase(accountType)) {
            newAccount = new SavingsAccount(customerId);
        } else {
            System.out.println("Invalid account type.");
            return -1;
        }

        newAccount.deposit(initialDeposit);
        accounts.put(accountId, newAccount);
        customer.addAccount(accountId);
        return accountId;
    }

    // Other methods like deposit, withdraw, checkBalance, etc.
    public void deposit(int accountId, double amount) {
        Account account = getAccount(accountId);
        if (account != null && amount > 0) {
            account.deposit(amount);
            System.out.println("Deposited $" + amount + " to account " + accountId);
        } else {
            System.out.println("Invalid account ID or deposit amount.");
        }
    }

    public void withdraw(int accountId, double amount) {
        Account account = getAccount(accountId);
        if (account != null && amount > 0) {
            account.withdraw(amount);
        } else {
            System.out.println("Invalid account ID or withdrawal amount.");
        }
    }

    public double checkBalance(int accountId) {
        Account account = getAccount(accountId);
        if (account != null) {
            return account.getBalance();
        } else {
            System.out.println("Account not found.");
            return 0.0;
        }
    }

    public String printStatement(int accountId) {
        Account account = getAccount(accountId);
        if (account != null) {
            StringBuilder statement = new StringBuilder("Account Statement for Account ID: " + accountId + "\n");
            for (Transaction transaction : account.getTransactions()) {
                statement.append(transaction.toString()).append("\n");
            }
            return statement.toString();
        } else {
            return "Account not found.";
        }
    }

    public void closeAccount(int accountId) {
        Account account = getAccount(accountId);
        if (account != null) {
            if (account.getBalance() == 0) {
                accounts.remove(accountId);
                System.out.println("Account " + accountId + " is closed.");
            } else {
                System.out.println("Account cannot be closed. Balance is not zero.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    // Utility method to get account by ID
    private Account getAccount(int accountId) {
        return accounts.get(accountId);
    }
}
