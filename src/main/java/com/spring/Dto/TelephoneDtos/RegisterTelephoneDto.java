package com.spring.Dto.TelephoneDtos;

/**
 * This record it'll be used to register a new telephone
 * @param ddd ddd of the telephone
 * @param number the number of the telephone*/
public record RegisterTelephoneDto(String ddd, String number){
}
