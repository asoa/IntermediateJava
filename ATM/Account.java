/*
 * File: Account.java
 * Author: Alex Bailey
 * Date: 8 April 18
 * Purpose: ATM has two accounts: Checking and Savings.  Account class has withdraw, deposit, transfer, and getBalance methods
 */

public class Account {
    // instance variables
    private double balance;
    static int numWithdrawals;
    private final double SERVICE_CHARGE = 1.5;

    // constructors
    public Account(){
        this.balance = 0.0;
        this.numWithdrawals = 0;
    }
    // getters and setters

    // methods
    public void withdraw(double amount) throws InsufficientFunds {
        if(amount > 0 && applyServiceCharge()) {
            if(balance < amount + SERVICE_CHARGE) { // amount + service charge > balance
                throw new InsufficientFunds();
            } else {
                balance -= (amount + SERVICE_CHARGE);  // balance > amount + service charge
                numWithdrawals++;
            }
        } else if (amount > 0) {
            if(balance < amount) {
                throw new InsufficientFunds();
            } else {
                balance -= amount;  // service charge not applied
                numWithdrawals++;
            }
        } else {}
    }
    // deposits amount parameter to balance
    public void deposit(double amount) {
        balance += amount;
    }
    // transfers amount parameter to accoutName
    public void transfer(double amount, Account accountName) throws InsufficientFunds {
        if(balance < amount) {
            throw new InsufficientFunds();
        }
        balance -= amount;
        accountName.deposit(amount);
    }
    // returns account balance
    public double getBalance() {
        return balance;
    }
    // returns true if more than 4 withdrawals have occurred
    public boolean applyServiceCharge() throws InsufficientFunds {
        if(numWithdrawals > 3) {
            if(balance - SERVICE_CHARGE < 0) {
                throw new InsufficientFunds();
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
