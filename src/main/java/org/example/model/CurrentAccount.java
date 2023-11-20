package org.example.model;

public class CurrentAccount extends BankAccount{
    private double overDraft;

    public CurrentAccount() {
        super();
    }

    public CurrentAccount(double balance, String currency, double overDraft) {
        super(balance, currency);
        this.overDraft = overDraft;
    }

    public CurrentAccount(String accountId, double balance, String currency, AccountStatus status, double overDraft) {
        super(accountId, balance, currency, status);
        this.overDraft = overDraft;
    }

    public double getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(double overDraft) {
        this.overDraft = overDraft;
    }

    @Override
    public String getType() {
        return "Current_Account";
    }

    @Override
    public String toString() {
        return "CurrentAccount "
                +super.toString()+
                ", overDraft=" + overDraft +'}' ;
    }
}
