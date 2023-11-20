package org.example.businessLayer;

import org.example.exceptions.AccountNotFoundException;
import org.example.exceptions.BalanceNotSufficientException;
import org.example.model.AccountStatus;
import org.example.model.BankAccount;
import org.example.model.CurrentAccount;
import org.example.model.SavingAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BankAccountServiceImpl implements BankAccountService {
    private List<BankAccount> accounts = new ArrayList<>();
    @Override
    public BankAccount addBankAccount(BankAccount account) {
        accounts.add(account);
        return account;
    }

    @Override
    public List<BankAccount> getAllAccounts() {
        return accounts;
    }

    @Override
    public BankAccount getAccountById(String id) throws AccountNotFoundException {
        for (BankAccount bankAccount:accounts)
            if (bankAccount.getAccountId().equals(id))
                return bankAccount;
        throw new AccountNotFoundException("BankAccount not found");

    }

    @Override
    public void addRandomData(int size) {
        AccountStatus[] statuses = AccountStatus.values();
        Random random = new Random();
        for (int i = 0 ; i < size ; i++){
            BankAccount bankAccount;
            if(Math.random()>0.5){
                bankAccount = new CurrentAccount(Math.round(Math.random()*90000),
                        Math.random()>0.5?"MAD":"EURO",
                        Math.round(Math.random()*5000));
                bankAccount.setStatus(statuses[random.nextInt(statuses.length)]);
            }

            else {
                bankAccount = new SavingAccount(Math.round(Math.random()*90000),
                        Math.random()>0.5?"MAD":"USD",
                        5+Math.round(Math.random()*10));
                bankAccount.setStatus(statuses[random.nextInt(statuses.length)]);
            }
            accounts.add(bankAccount);
        }
        //expression ternaire : condition?siOK:siNon

    }

    @Override
    public void credit(String id, double amount) throws AccountNotFoundException {
        BankAccount account = getAccountById(id);
        account.setBalance(account.getBalance()+amount);
    }

    @Override
    public void debit(String id, double amount) throws AccountNotFoundException, BalanceNotSufficientException {
        BankAccount account = getAccountById(id);
        double availableBalance = account.getBalance();
        if (account instanceof CurrentAccount)
            availableBalance = account.getBalance()+((CurrentAccount) account).getOverDraft();
        if (availableBalance < amount)
            throw new BalanceNotSufficientException("Balance not sufficient");
        else
            account.setBalance(account.getBalance()-amount);
    }
    //methode doit être transactional
    @Override
    public void transfer(String idAccountSource, String idAccountDestination, double amount)  throws AccountNotFoundException, BalanceNotSufficientException{

        debit(idAccountSource,amount);
        credit(idAccountDestination,amount);
    }

    @Override
    public List<BankAccount> getSavingAccount() {
        //Imperative Approach
        /*
        List<BankAccount> bankAccountList = new ArrayList<>();
        for (BankAccount account: accounts){
            if (account instanceof SavingAccount)
                bankAccountList.add(account);
        }
        return bankAccountList;

         */

        // Declarative approach
        return accounts.stream().filter(bankAccount -> bankAccount instanceof SavingAccount).collect(Collectors.toList());
    }

    @Override
    public List<BankAccount> getCurrentAccount() {

        //Imperative Approach
        /*
        List<BankAccount> bankAccountList = new ArrayList<>();
        for (BankAccount account: accounts){
            if (account.getType().equals("Current_Account"))
                bankAccountList.add(account);
        }
        return bankAccountList;
         */

        // Declarative approach
        return accounts.stream().filter(bankAccount -> bankAccount instanceof CurrentAccount).collect(Collectors.toList());

    }

    @Override
    public double getTotalBalance() {

        /*
        double total = 0;
        for (BankAccount account: accounts)
            total = total+account.getBalance();
        return total;
         */
        return accounts.stream().map(bankAccount -> bankAccount.getBalance()).reduce(0.0,((accumulateur, value) -> accumulateur+value));

    }

    @Override
    public List<BankAccount> searchAccounts(Condition<BankAccount> condition) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        for (BankAccount bankAccount : accounts) {
            if(condition.test(bankAccount))
                bankAccounts.add(bankAccount);
        }
        return bankAccounts;
    }
}
