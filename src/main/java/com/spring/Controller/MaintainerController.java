package com.spring.Controller;

import com.spring.Dto.MaintainerDtos.MaintainerDto;
import com.spring.Dto.MaintainerDtos.RegisterMaintainerDto;
import com.spring.Entity.BankAccount;
import com.spring.Entity.Maintainer;
import com.spring.Entity.Telephone;
import com.spring.Repository.MaintainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/maintainers")
public class MaintainerController {

    @Autowired
    private MaintainerRepository maintainerRepository;


    @GetMapping
    public ResponseEntity<List<MaintainerDto>> getMaintainers() {
        return ResponseEntity.ok().body(maintainerRepository.findAll()
                .stream()
                .map(MaintainerDto::new).toList());
    }

    @PostMapping
    public ResponseEntity<MaintainerDto> createMaintainer(@RequestBody RegisterMaintainerDto registerMaintainerDto) {
        // Create the Maintainer entity
        Maintainer maintainer = new Maintainer();
        maintainer.setName(registerMaintainerDto.name());

        // Create and set up BankAccount entities
        Set<BankAccount> bankAccounts = registerMaintainerDto.bankAccounts().stream()
                .map(r -> {
                    BankAccount bankAccount = new BankAccount(r.name(), r.agency(), r.number(), r.isActive());
                    bankAccount.setMaintainer(maintainer);  // Set the relationship to Maintainer
                    return bankAccount;
                })
                .collect(Collectors.toSet());

        maintainer.setBankAccounts(bankAccounts);

        // Create and set up the Telephone entity
        Telephone telephone = registerMaintainerDto.telephone() != null ?
                new Telephone(registerMaintainerDto.telephone().ddd(), registerMaintainerDto.telephone().number()) : null;

        if (telephone != null) {
            telephone.setMaintainer(maintainer);  // Set the relationship to Maintainer
            maintainer.setTelephone(telephone);
        }

        // Save the Maintainer entity
        Maintainer savedMaintainer = maintainerRepository.save(maintainer);

        // Return the saved Maintainer wrapped in a DTO
        return ResponseEntity.ok(new MaintainerDto(savedMaintainer));
    }

}
