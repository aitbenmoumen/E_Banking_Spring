package com.aitbenmoumen.e_bank.web;

import com.aitbenmoumen.e_bank.dtos.CustomerDTO;
import com.aitbenmoumen.e_bank.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor @Slf4j
public class CustomerRestController {
    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    public List<CustomerDTO> listCustomers() {
        return bankAccountService.listCustumers();
    }
}
