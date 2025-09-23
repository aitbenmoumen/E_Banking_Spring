package com.aitbenmoumen.e_bank.mappers;

import com.aitbenmoumen.e_bank.dtos.CustomerDTO;
import com.aitbenmoumen.e_bank.entities.BankAccount;
import com.aitbenmoumen.e_bank.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(customer.getId());
//        customerDTO.setName(customer.getName());
//        customerDTO.setEmail(customer.getEmail());
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }
    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
//        customer.setId(customerDTO.getId());
//        customer.setName(customerDTO.getName());
//        customer.setEmail(customerDTO.getEmail());
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }
}
