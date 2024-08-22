package com.spring.Dto.BankAccountDtos;

/**
 * This record it'll be used as method to register a new bank account
 * @param name name of the bank
 * @param agency number of the bank account agency
 * @param number number of the bank account
 * @param isActive information if the bank account it's active*/
public record RegisterBankAccountDto (String name, String agency, String number, boolean isActive){
}
