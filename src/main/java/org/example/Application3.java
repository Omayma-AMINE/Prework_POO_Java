package org.example;

import org.example.businessLayer.BankAccountService;
import org.example.businessLayer.BankAccountServiceImpl;
import org.example.exceptions.AccountNotFoundException;
import org.example.exceptions.BalanceNotSufficientException;
import org.example.model.BankAccount;
import org.example.model.CurrentAccount;
import org.example.model.SavingAccount;

import java.util.List;
import java.util.function.Consumer;

public class Application3 {
    public static void main(String[] args) {
        BankAccountService accountService = new BankAccountServiceImpl();
        accountService.addBankAccount(new CurrentAccount(4500,"MAD",2500));
        accountService.addBankAccount(new SavingAccount(9700,"EURO",3.5));
        accountService.addBankAccount(new SavingAccount(678,"EURO",0.5));
        accountService.addBankAccount(new SavingAccount(778,"EURO",2.5));
        accountService.addBankAccount(new SavingAccount(500,"EURO",5));
        List<BankAccount> accountList = accountService.getAllAccounts();

        /*
        for(int i = 0; i< accountList.size(); i++)
               System.out.println(accountList.get(i).toString());
         */

        /*
        for (BankAccount bankAccount: accountList)
            System.out.println(bankAccount.toString());
         */
        /*
        accountList.forEach(new Consumer<BankAccount>() {
            @Override
            public void accept(BankAccount account) {
                System.out.println(account.toString());
            }
        });
         */
        accountList.forEach(account ->
                System.out.println(account.toString())
                            );
        System.out.println("\n===================================");
        System.out.println("Operation de debit d'un montant qui dépasse le solde disponible ");
        System.out.println("L'etat de compte avant l'operation : "+accountList.get(0).toString());
        // l'equivalence de la fonction lambda avec un seul parametre et une seule instruction
        // accountList.forEach(System.out::println);

        try {
           // BankAccount accountById = accountService.getAccountById("15H");
            accountService.debit(accountList.get(0).getAccountId(),10000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("L'etat de compte après l'operation : "+accountList.get(0).toString());
        System.out.println("\n==========================================");
        /*accountList.forEach(account ->
                System.out.println(account.toString())
        );*/
        System.out.println("Operation de crédit ");
        System.out.println("L'etat de compte avant l'operation  : "+accountList.get(1).toString());

        try {
            accountService.credit(accountList.get(1).getAccountId(),900);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("L'etat de compte après l'operation  : "+accountList.get(1).toString());
        System.out.println("\n==========================================");
        /*accountList.forEach(account ->
                System.out.println(account.toString())
        );*/
        System.out.println("Operation de transfert ");
        System.out.println("L'etat des deux comptes avant l'operation  : ");
        System.out.println("Le compte à débiter : "+accountList.get(0).toString());
        System.out.println("Le compte à créditer : "+accountList.get(1).toString());


        try {
            accountService.transfer(accountList.get(0).getAccountId(),accountList.get(1).getAccountId(),2000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("L'etat des deux comptes après l'operation  : ");
        System.out.println("Le compte à débiter : "+accountList.get(0).toString());
        System.out.println("Le compte à créditer : "+accountList.get(1).toString());
        System.out.println("\n==========================================");
        /*accountList.forEach(account ->
                System.out.println(account.toString())
        );*/

        System.out.println("\nSaving Accounts : ");
        accountService.getSavingAccount().forEach(bankAccount ->
                System.out.println(bankAccount.toString()));
        System.out.println("\nCurrent Accounts :");
        accountService.getCurrentAccount().forEach(bankAccount ->
                System.out.println(bankAccount.toString()));
        System.out.println("\n==========================================");

        System.out.println("\n Total balance : "+accountService.getTotalBalance());
        System.out.println("\n Account with banlance > 1000 : ");
        accountService.searchAccounts(account -> (account.getBalance()>1000))
                .forEach(account -> System.out.println(account));


    }
}
