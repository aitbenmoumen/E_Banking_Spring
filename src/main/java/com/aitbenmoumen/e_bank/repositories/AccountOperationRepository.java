package com.aitbenmoumen.e_bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aitbenmoumen.e_bank.entities.AccountOperation;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {

}
// This interface extends JpaRepository to provide CRUD operations for AccountOperation entities.
// It allows for operations such as saving, deleting, and finding AccountOperation entities by their ID.