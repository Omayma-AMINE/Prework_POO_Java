package org.example;

import org.example.businessLayer.BankAccountService;
import org.example.businessLayer.BankAccountServiceImpl;

public class Application4 {
    public static void main(String[] args) {
        BankAccountService bankAccountService = new BankAccountServiceImpl();
        bankAccountService.addRandomData(10);
        bankAccountService.getAllAccounts().forEach(System.out::println);
    }
}
