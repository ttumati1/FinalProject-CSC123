package com.bankapp;

public class SavingsAccount extends Account {
    private double interestRate; // Annual interest rate

    public SavingsAccount(int customerId) {
        super(customerId);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            this.balance -= amount;
            transactions.add(new Transaction("Debit", amount));
        } else {
            // Handle cases where there are insufficient funds or the amount is invalid
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }

    // You can add more methods specific to savings accounts if needed
}
