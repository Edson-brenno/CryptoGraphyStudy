package com.spring.Dto.MaintainerDtos;

import com.spring.Dto.BankAccountDtos.RegisterBankAccountDto;
import com.spring.Dto.TelephoneDtos.RegisterTelephoneDto;
import java.util.Set;

/**
 * This dto it'll be used to register a new maintainer
 * @param name name of the maintainer
 * @param bankAccounts set of bank account to be registered with relation with the maintainer
 * @param telephone telephone to be registered with relation with the maintainer*/
public record RegisterMaintainerDto (String name, Set<RegisterBankAccountDto> bankAccounts,
                                     RegisterTelephoneDto telephone){
}
