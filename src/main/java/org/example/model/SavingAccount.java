package org.example.model;

public class SavingAccount extends BankAccount{
    private double interestRate ;

    public SavingAccount() {
        super();
    }


    public SavingAccount(double balance, String currency, double interestRate) {
        super(balance, currency);
        this.interestRate = interestRate;
    }

    public SavingAccount(String accountId, double balance, String currency, AccountStatus status, double interestRate) {
        super(accountId, balance, currency, status);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String getType() {
        return "Saving_Account";
    }
    @Override
    public String toString() {
        return "SavingAccount  " +
                super.toString() +
                ", interestRate=" + interestRate +'}' ;
    }
}
