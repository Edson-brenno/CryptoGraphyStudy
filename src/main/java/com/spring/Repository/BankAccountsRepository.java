package com.spring.Repository;

import com.spring.Entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountsRepository extends JpaRepository<BankAccount, Long> {
}
