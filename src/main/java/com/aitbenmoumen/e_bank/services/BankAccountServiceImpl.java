package com.aitbenmoumen.e_bank.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;



import org.springframework.stereotype.Service;

import com.aitbenmoumen.e_bank.entities.AccountOperation;
import com.aitbenmoumen.e_bank.entities.BankAccount;
import com.aitbenmoumen.e_bank.entities.CurrentAccount;
import com.aitbenmoumen.e_bank.entities.Customer;
import com.aitbenmoumen.e_bank.entities.SavingAccount;
import com.aitbenmoumen.e_bank.enums.OperationType;
import com.aitbenmoumen.e_bank.repositories.AccountOperationRepository;
import com.aitbenmoumen.e_bank.repositories.BankAccountRepository;
import com.aitbenmoumen.e_bank.repositories.CustomerRepository;
import com.aitbenmoumen.exceptions.BankAccountNotFoundException;
import com.aitbenmoumen.exceptions.CustomerNotFoundException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@AllArgsConstructor
@Slf4j // for logging --> inside every method

public class BankAccountServiceImpl implements BankAccountService {
    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;

    // Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Saving a new customer");
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> listCustumers() {
        return customerRepository.findAll();
    }

    @Override
    public BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException{
        Long id = Long.parseLong(accountId);
        BankAccount bank = bankAccountRepository.findById(id)
        .orElseThrow(()-> new BankAccountNotFoundException("Bank account does not exist !!"));
        return bank;
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount account = getBankAccount(accountId);
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance for debit");
        }
        account.setBalance(account.getBalance() - amount);
        bankAccountRepository.save(account);
        
        AccountOperation operation = new AccountOperation(null, new Date(), amount, OperationType.DEBIT, account, description);
        accountOperationRepository.save(operation);
        log.info("Debited " + amount + " from " + accountId + ": " + description);
    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount account = getBankAccount(accountId);
        account.setBalance(account.getBalance() + amount);
        bankAccountRepository.save(account);

        accountOperationRepository.save(new AccountOperation(null, new Date(), amount, OperationType.CREDIT, account, description));
        log.info("Credited " + amount + " to " + accountId + ": " + description);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException {
        debit(accountIdSource, amount, "Transfer to " + accountIdDestination);
        credit(accountIdDestination, amount, "Transfer from " + accountIdSource);
    }

    @Override
    public BankAccount saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new CustomerNotFoundException("Customer not found !!"));

        CurrentAccount bankAccount = new CurrentAccount();
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setBalance(initialBalance);
        bankAccount.setCreatedAt(new Date());
        bankAccount.setOverdraftLimit(overDraft);
        bankAccount.setCustomer(customer);

        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount saveSavingBankAccount(double initialBalance, Long customerId, double intrestRate) throws CustomerNotFoundException {
         Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new CustomerNotFoundException("Customer not found !!"));

        SavingAccount bankAccount = new SavingAccount();
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setBalance(initialBalance);
        bankAccount.setCreatedAt(new Date());
        bankAccount.setInterestRate(intrestRate);
        bankAccount.setCustomer(customer);

        return bankAccountRepository.save(bankAccount);
    }

}
