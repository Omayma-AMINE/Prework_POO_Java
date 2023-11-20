package org.example.businessLayer;

import org.example.exceptions.AccountNotFoundException;
import org.example.exceptions.BalanceNotSufficientException;
import org.example.model.BankAccount;

import java.util.List;

public interface BankAccountService {

   BankAccount addBankAccount(BankAccount account);
   List<BankAccount> getAllAccounts();
   BankAccount getAccountById(String id ) throws AccountNotFoundException;
   void addRandomData(int size);
   void credit(String id, double amount) throws AccountNotFoundException;
   void debit(String id , double amount) throws AccountNotFoundException, BalanceNotSufficientException;
   void transfer (String idAccountSource , String idAccountDestination, double amount) throws AccountNotFoundException, BalanceNotSufficientException;
   List<BankAccount> getSavingAccount();
   List<BankAccount> getCurrentAccount();
   double getTotalBalance();
   List<BankAccount> searchAccounts(Condition<BankAccount> condition);

}
