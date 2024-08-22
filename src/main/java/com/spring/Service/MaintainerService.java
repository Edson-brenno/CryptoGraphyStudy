package com.spring.Service;

import com.spring.Dto.MaintainerDtos.MaintainerDto;
import com.spring.Dto.MaintainerDtos.RegisterMaintainerDto;
import com.spring.Entity.BankAccount;
import com.spring.Entity.Maintainer;
import com.spring.Entity.Telephone;
import com.spring.Repository.MaintainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MaintainerService {

    @Autowired
    private MaintainerRepository maintainerRepository;

    /**
     * This method it'll return all the maintainers that are registered*/
    public List<MaintainerDto> getAllMaintainers() {
        return maintainerRepository.findAll()
                .stream()
                .map(MaintainerDto::new).toList();
    }

    /**
     * This method it'll register a new maintainer and the maintainer relations
     * @param registerMaintainerDto dto used to register a new maintainer*/
    public MaintainerDto createMaintainer(RegisterMaintainerDto registerMaintainerDto) {
        Maintainer maintainer = new Maintainer();

        maintainer.setName(registerMaintainerDto.name()); // Set the name of the maintainer

        /*Set the telephone that it'll be created*/
        maintainer.setTelephone(
                // New stream for the register
                Stream.of(registerMaintainerDto.telephone())
                        .map(telephone -> {
                            Telephone newTelephone = new Telephone(telephone.ddd(), telephone.number());
                            newTelephone.setMaintainer(maintainer);
                            return newTelephone;
                        }).findFirst().orElse(null)
        );

        /*Set the telephone that it'll be created*/
        maintainer.setBankAccounts(
                registerMaintainerDto.bankAccounts()
                        .stream()
                        .map(r -> {
                            BankAccount newBankAccount = new BankAccount(r.name(), r.agency(), r.number(), r.isActive());
                            newBankAccount.setMaintainer(maintainer);
                            return newBankAccount;
                        }).collect(Collectors.toSet()));


        return new MaintainerDto(maintainerRepository.save(maintainer));
    }
}
