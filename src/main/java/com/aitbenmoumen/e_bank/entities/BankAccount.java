package com.aitbenmoumen.e_bank.entities;

import com.aitbenmoumen.e_bank.enums.AccountStatus;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Using single table inheritance for BankAccount and its subclasses
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // using multiple tables to represent each class(the Bank account table won't be created) 
// @DiscriminatorColumn(name = "type", length = 4, discriminatorType = DiscriminatorType.STRING) // Discriminator column to differentiate between subclasses
//@Inheritance(strategy = InheritanceType.JOINED)
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class BankAccount { // abstract class so it won't be created in th DB
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING) // to have the enum format in the database as string
    private AccountStatus status;
    @ManyToOne
    private Customer customer;  // to specify the cardinalities
    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY) // The eager is to make the fetching operation load not only the class attributes but also the list of operations 
    List<AccountOperation> accountOperations; // Assuming AccountOperation is another entity that you have defined
}
