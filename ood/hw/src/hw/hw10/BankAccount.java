package hw.hw10;

import java.io.Serializable;

public class BankAccount implements Serializable {

    private String username;
    private Integer balance;

    public BankAccount(String username) {
        this.username = username;
        this.balance = 0;
    }

    public String getUsername() {
        return username;
    }

    public Integer getBalance() {
        return balance;
    }

    public void deposit(Integer amount) {
        balance += amount;
    }

    public void withdraw(Integer amount) {
        balance -= amount;
    }

    public String toString() {
        return "Username: " + getUsername() + " Balance: " + getBalance();
    }

}