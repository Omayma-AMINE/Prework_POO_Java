package org.example.model;

import java.util.Objects;
import java.util.UUID;

public abstract class BankAccount {
    private String accountId;
    private double balance ;
    private String currency;
    private AccountStatus status ;

    public BankAccount(){
        accountId = UUID.randomUUID().toString();
        status = AccountStatus.CREATED;
    }

    public BankAccount(double balance, String currency) {
        accountId = UUID.randomUUID().toString();
        status = AccountStatus.CREATED;
        this.balance = balance;
        this.currency = currency;
    }

    public BankAccount(String accountId, double balance, String currency, AccountStatus status) {
        this.accountId = accountId;
        this.balance = balance;
        this.currency = currency;
        this.status = status;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public abstract String getType();
    @Override
    public String toString() {
        return "{" +
                "accountId='" + accountId + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", status=" + status + '\''
                ;
    }

    /*@Override
    public boolean equals(Object object) {
        BankAccount account  = (BankAccount) object;
        if (this.accountId.equals(account.accountId))
            return true ;
        else
            return false;
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(accountId)+
                Objects.hash(balance)+
                Objects.hash(currency)+
                Objects.hash(status);
    }
}
