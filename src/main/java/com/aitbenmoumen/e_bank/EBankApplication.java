package com.aitbenmoumen.e_bank;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.aitbenmoumen.e_bank.entities.AccountOperation;
import com.aitbenmoumen.e_bank.entities.CurrentAccount;
import com.aitbenmoumen.e_bank.entities.Customer;
import com.aitbenmoumen.e_bank.entities.SavingAccount;
import com.aitbenmoumen.e_bank.enums.AccountStatus;
import com.aitbenmoumen.e_bank.enums.OperationType;
import com.aitbenmoumen.e_bank.repositories.AccountOperationRepository;
import com.aitbenmoumen.e_bank.repositories.BankAccountRepository;
import com.aitbenmoumen.e_bank.repositories.CustomerRepository;

@SpringBootApplication
public class EBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBankApplication.class, args);
    }
    @Bean
    CommandLineRunner start(
        CustomerRepository customerRepository,
        BankAccountRepository bankAccountRepository,
        AccountOperationRepository accountOperationRepository
    ){
        return args -> {
            Stream.of("John Doe", "Jane Smith", "Alice Johnson")
                .forEach(name -> {
                    Customer customer = new Customer();
                    customer.setName(name);
                    customer.setEmail(name.toLowerCase().replace(" ", ".") + "@example.com");
                    customerRepository.save(customer);
                });
            customerRepository.findAll().forEach(customer -> {
                CurrentAccount account = new CurrentAccount();
                account.setId(UUID.randomUUID().toString());
                account.setBalance(Math.random() * 10000);
                account.setCreatedAt(new Date());
                account.setStatus(AccountStatus.CREATED);
                account.setCustomer(customer);
                account.setOverdraftLimit(9000);
                bankAccountRepository.save(account);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random() * 10000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(customer);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
            });
            bankAccountRepository.findAll().forEach(acc ->{
                for(int i =0; i < 5 ; i++){
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setAmount(Math.random()*10000);
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setOperationType(Math.random() > 0.5 ? OperationType.CREDIT : OperationType.DEBIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                    
                }
            });

        };

    }
}
