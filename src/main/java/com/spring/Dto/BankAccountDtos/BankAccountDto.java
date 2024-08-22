package com.spring.Dto.BankAccountDtos;

import com.spring.Entity.BankAccount;

/**
 * This record it'll be used as method to return bank account information
 * @param name name of the bank
 * @param agency number of the bank account agency
 * @param number number of the bank account
 * @param isActive information if the bank account it's active*/
public record BankAccountDto(String name, String agency, String number, boolean isActive) {
    public BankAccountDto(BankAccount bankAccount){
        this(bankAccount.getNameBank(), bankAccount.getAgency(), bankAccount.getNumber(), bankAccount.isActive());
    }
}
