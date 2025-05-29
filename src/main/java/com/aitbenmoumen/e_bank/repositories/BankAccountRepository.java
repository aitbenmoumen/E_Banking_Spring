package com.aitbenmoumen.e_bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aitbenmoumen.e_bank.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}
