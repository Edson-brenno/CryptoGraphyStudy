package com.spring.Dto.MaintainerDtos;

import com.spring.Dto.BankAccountDtos.BankAccountDto;
import com.spring.Dto.TelephoneDtos.TelephoneDto;
import com.spring.Entity.Maintainer;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * This dto it'll be used to return the important information for the user
 * @param name name of the maintainer
 * @param bankAccounts set of bank account that has relation with the maintainer
 * @param telephone telephone that has relation with the maintainer*/
public record MaintainerDto(String name, Set<BankAccountDto> bankAccounts, TelephoneDto telephone) {
    public MaintainerDto(Maintainer maintainer) {
        this(maintainer.getName(),
                // Generate the set of bank account
                maintainer.getBankAccounts()
                        .stream()
                        .map(BankAccountDto::new).collect(Collectors.toSet()),
                // The information of the telephone
                new TelephoneDto(maintainer.getTelephone()));
    }
}
