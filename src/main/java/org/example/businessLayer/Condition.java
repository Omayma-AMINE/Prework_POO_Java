package org.example.businessLayer;

import org.example.model.BankAccount;

public interface Condition<T> {
    boolean test(T objet);
}
