package com.aitbenmoumen.e_bank.web;

import com.aitbenmoumen.e_bank.dtos.CustomerDTO;
import com.aitbenmoumen.e_bank.repositories.CustomerRepository;
import com.aitbenmoumen.e_bank.services.BankAccountService;
import com.aitbenmoumen.exceptions.CustomerNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor @Slf4j
public class CustomerRestController {
    private BankAccountService bankAccountService;
    private CustomerRepository customerRepository;
    @GetMapping("/customers")
    public List<CustomerDTO> listCustomers() {
        return bankAccountService.listCustumers();
    }
    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws Exception {
        return bankAccountService.getCustomerById(customerId);
    }
    @PostMapping("/customers")
    public CustomerDTO createCustomer(@RequestBody CustomerDTO req) {
        return bankAccountService.saveCustomer(req);
    }
}
