package com.bankapp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Customer {
    private int customerId;
    private String name;
    private Date dateOfBirth;
    private String address;
    private Set<Integer> accountIds; // Set of account IDs associated with the customer

    public Customer(int customerId, String name, Date dateOfBirth, String address) {
        this.customerId = customerId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.accountIds = new HashSet<>();
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addAccount(int accountId) {
        accountIds.add(accountId);
    }

    public Set<Integer> getAccountIds() {
        return accountIds;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", accountIds=" + accountIds +
                '}';
    }
}
