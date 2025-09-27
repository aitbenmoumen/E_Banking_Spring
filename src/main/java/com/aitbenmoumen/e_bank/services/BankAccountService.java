package com.aitbenmoumen.e_bank.services;

import java.util.List;


import com.aitbenmoumen.e_bank.dtos.CustomerDTO;
import com.aitbenmoumen.e_bank.entities.BankAccount;
import com.aitbenmoumen.e_bank.entities.Customer;
import com.aitbenmoumen.exceptions.BankAccountNotFoundException;
import com.aitbenmoumen.exceptions.CustomerNotFoundException;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customer);
    BankAccount saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException;
    BankAccount saveSavingBankAccount(double initialBalance, Long customerId, double intrestRate) throws CustomerNotFoundException;
    List<CustomerDTO> listCustumers();
    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException ;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException;
    public CustomerDTO getCustomerById(Long id) throws Exception;
}
