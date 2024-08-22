package com.spring.Dto.TelephoneDtos;

import com.spring.Entity.Telephone;

/**
 * This record it'll be used to return the necessary information to the user
 * @param ddd ddd of the telephone
 * @param number the number of the telephone*/
public record TelephoneDto(String ddd, String number) {
    public TelephoneDto(Telephone telephone) {
        this(telephone.getDdd(), telephone.getNumber());
    }
}
