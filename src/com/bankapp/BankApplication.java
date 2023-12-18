package com.bankapp;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankApplication {

    private static Bank bank = new Bank();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nWelcome to the Bank Application");
            System.out.println("1. Add Customer");
            System.out.println("2. Open Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Check Account Balance");
            System.out.println("6. Print Account Statement");
            System.out.println("7. Close Account");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    openAccount();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    withdrawMoney();
                    break;
                case 5:
                    checkBalance();
                    break;
                case 6:
                    printStatement();
                    break;
                case 7:
                    closeAccount();
                    break;
                case 8:
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCustomer() {
        // Implementation to add a customer
        System.out.println("Enter customer name: ");
        String name = scanner.next();
        System.out.println("Enter date of birth (dd/MM/yyyy): ");
        String dobStr = scanner.next();
        System.out.println("Enter address: ");
        String address = scanner.next();

        try {
            Date dob = dateFormat.parse(dobStr);
            int customerId = bank.addCustomer(name, dob, address);
            System.out.println("Customer added successfully. Customer ID is: " + customerId);
        } catch (Exception e) {
            System.out.println("Invalid date format.");
        }
    }

    private static void openAccount() {
        System.out.println("Enter customer ID: ");
        int customerId = scanner.nextInt();

        System.out.println("Enter account type (checking/savings): ");
        String accountType = scanner.next();

        System.out.println("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();

        if (initialDeposit < 0) {
            System.out.println("Initial deposit cannot be negative.");
            return;
        }

        int accountId = bank.openAccount(customerId, accountType, initialDeposit);
        if (accountId != -1) {
            System.out.println("Account opened successfully. Account ID is: " + accountId);
        } else {
            System.out.println("Failed to open account. Please check the details and try again.");
        }
    }

    private static void depositMoney() {
        System.out.println("Enter account ID: ");
        int accountId = scanner.nextInt();

        System.out.println("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("The deposit amount must be greater than zero.");
            return;
        }

        bank.deposit(accountId, amount);
        System.out.println("Amount deposited successfully.");
    }

    private static void withdrawMoney() {
        System.out.println("Enter account ID: ");
        int accountId = scanner.nextInt();

        System.out.println("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("The withdrawal amount must be greater than zero.");
            return;
        }

        // Call the Bank class's method to withdraw money
        bank.withdraw(accountId, amount);
        System.out.println("Withdrawal successful.");
    }

    private static void checkBalance() {
        System.out.println("Enter account ID: ");
        int accountId = scanner.nextInt();

        double balance = bank.checkBalance(accountId);
        if (balance >= 0) {
            System.out.println("The current balance for account " + accountId + " is: $" + balance);
        } else {
            System.out.println("Account not found. Please check the account ID and try again.");
        }
    }

    private static void printStatement() {
        System.out.println("Enter account ID: ");
        int accountId = scanner.nextInt();

        String statement = bank.printStatement(accountId);
        if (statement != null && !statement.isEmpty()) {
            System.out.println("Account Statement for Account ID " + accountId + ":");
            System.out.println(statement);
        } else {
            System.out.println("No statement available for Account ID " + accountId +
                    " or account not found.");
        }
    }

    private static void closeAccount() {
        System.out.println("Enter account ID to close: ");
        int accountId = scanner.nextInt();

        bank.closeAccount(accountId);
        System.out.println("Account " + accountId + " has been closed.");
    }

    // Implement other methods (depositMoney, withdrawMoney, checkBalance,
    // printStatement, closeAccount)

    // Utility methods to interact with the user and perform operations can be added
    // here
}
