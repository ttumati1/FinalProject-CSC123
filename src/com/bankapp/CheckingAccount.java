package com.bankapp;

public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(int customerId, double overdraftLimit) {
        super(customerId);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance - amount >= -overdraftLimit)) {
            this.balance -= amount;
            transactions.add(new Transaction("Debit", amount));
        } else {
            // Handle cases where the withdrawal exceeds the overdraft limit or amount is
            // invalid
            System.out.println("Withdrawal exceeds overdraft limit or amount is invalid.");
        }
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}
