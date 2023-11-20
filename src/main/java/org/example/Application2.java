package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.BankAccount;
import org.example.model.CurrentAccount;
import org.example.model.SavingAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application2 {

    public static void main(String[] args) {

        System.out.println("\n---------------------- Les tableaux : ----------------------\n");
        BankAccount [] accounts = new BankAccount[3];
        accounts[0] = new CurrentAccount(9600,"MAD",1000);
        accounts[1] = new CurrentAccount(5660,"MAD",5000);
        accounts[2] = new SavingAccount(5460,"USD",20);

        for(BankAccount account:accounts)
            System.out.println(toJson(account));

        for (BankAccount account: accounts){
            if (account instanceof CurrentAccount)
                System.out.println("Account n° "+account.getAccountId()+" is  : "+((CurrentAccount)account).getType()
                                    + "\tOver draft is : " + ((CurrentAccount) account).getOverDraft());
            else if (account instanceof SavingAccount)
                System.out.println("Account n° "+account.getAccountId()+" is  : "+((SavingAccount)account).getType()
                                    + "\tInterest Rate is : " + ((SavingAccount) account).getInterestRate());
        }

        System.out.println("\n---------------------- Les listes : ----------------------\n");
        List<BankAccount> bankAccountList = new ArrayList<>();
        bankAccountList.add(new SavingAccount(8900,"euro",15));
        bankAccountList.add(new SavingAccount(5000,"USD",25));
        bankAccountList.add(new CurrentAccount(4250,"euro",1500));
        bankAccountList.add(new CurrentAccount(1500,"MAD",0));

        for (BankAccount bankAccount:bankAccountList)
            System.out.println(toJson(bankAccount));

        System.out.println("\n---------------------- Les MAP : ----------------------\n");

        Map<String ,BankAccount> bankAccountMap = new HashMap<>();
        bankAccountMap.put("Account 1", new CurrentAccount(9637,"mad",1500));
        bankAccountMap.put("Account 2", new SavingAccount());

        for (String key : bankAccountMap.keySet())
            System.out.println(bankAccountMap.get(key));
        System.out.println("------------------------");
        for (BankAccount bankAccount : bankAccountMap.values())
            System.out.println(toJson(bankAccount));

    }

    public static String toJson(Object o){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //return objectMapper.writeValueAsString(o);

            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
