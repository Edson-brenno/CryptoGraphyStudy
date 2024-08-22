package com.spring.Entity.Converter;

import com.spring.Service.Utilities.EncryptationUtility;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * This converter it'll be responsible crypt and decrypt*/
@Converter(autoApply = true)
public class CryptConverter implements AttributeConverter<String, String> {

    private final EncryptationUtility encryptationUtility = new EncryptationUtility();

    /**
     * This method it'll be used when the user it's attempting to persist on the database
     * what it'll do is that it'll take the string and save on the database with cryptography */
    @Override
    public String convertToDatabaseColumn(String s) {
        if (s == null || s.isEmpty()) return null;

        try {
            return encryptationUtility.encrypt(s);
        }catch (Exception e) {
            return null;
        }
    }

    /**
     * This method it'll be used when the user it's attempting to persist on the database
     * what it'll do is that it'll take the data that are encrypted it'll decrypt for to return to user */
    @Override
    public String convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) return null;

        try {
            return encryptationUtility.decrypt(s);
        }catch (Exception e) {
            return null;
        }
    }
}
