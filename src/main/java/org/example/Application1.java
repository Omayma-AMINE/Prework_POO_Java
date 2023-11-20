package org.example;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.BankAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.CurrentAccount;
import org.example.model.SavingAccount;


public class Application1 {
    public static void main(String[] args) {

        BankAccount bankAccount1 = new CurrentAccount(1500, "euro",900);
        BankAccount bankAccount2 = new SavingAccount(9300,"MAD",5);
        BankAccount bankAccount3 = new CurrentAccount(1500, "euro",900);
        bankAccount3.setAccountId(bankAccount1.getAccountId());

        System.out.println("\n---------------------- Affichage des comptes - Fonction toString- ----------------------\n");
        System.out.println(bankAccount1.toString());
        System.out.println(bankAccount2.toString());
        System.out.println(bankAccount3.toString());

        System.out.println("\n---------------------- Comparaison avec la fonction EQUALS ----------------------\n");
        System.out.println("comparaison du BankAccount1 et BankAccount2 : ");
        compareAccount(bankAccount1,bankAccount2);
        System.out.println("comparaison du BankAccount1 et BankAccount3 : ");
        compareAccount(bankAccount1,bankAccount3);

        System.out.println("\n---------------------- Affichages des HashCodes ----------------------\n");
        System.out.println("BankAccount 1 : "+bankAccount1.hashCode());
        System.out.println("BankAccount 2 : "+bankAccount2.hashCode());
        System.out.println("BankAccount 3 : "+bankAccount3.hashCode());

        System.out.println("\n---------------------- Comparaison des HashCodes ----------------------\n");
        System.out.println("Comparaison du HashCoe de BankAccount1 et BankAccount2 :");
        compareAccountHashCode(bankAccount1, bankAccount2);
        System.out.println("Comparaison du HashCoe de BankAccount1 et BankAccount3 :");
        compareAccountHashCode(bankAccount1, bankAccount3);



    }

    public static void compareAccount (BankAccount account1, BankAccount account2){
        if(account1.equals(account2))
            System.out.println("\t\t\t\t\tLes deux comptes sont égaux");
        else
            System.out.println("\t\t\t\t\tLes deux comptes ne sont pas égaux");
    }
    public static void compareAccountHashCode(BankAccount account1, BankAccount account2){
        if (account1.hashCode() == account2.hashCode())
            System.out.println("\t\t\t\t\tLes deux comptes ont le meme contenu (HashCode) ");
        else
            System.out.println("\t\t\t\t\tLes deux comptes n'ont pas le meme contenu (HashCode) ");
    }


}