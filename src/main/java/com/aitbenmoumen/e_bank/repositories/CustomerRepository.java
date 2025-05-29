package com.aitbenmoumen.e_bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aitbenmoumen.e_bank.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
