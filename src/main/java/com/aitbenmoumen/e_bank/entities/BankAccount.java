package com.aitbenmoumen.e_bank.entities;

import com.aitbenmoumen.e_bank.enums.AccountStatus;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Using single table inheritance for BankAccount and its subclasses
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(name = "type", length = 4, discriminatorType = DiscriminatorType.STRING) // Discriminator column to differentiate between subclasses
@Data @NoArgsConstructor @AllArgsConstructor
public class BankAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING) // to have the enum format in the database as string
    private AccountStatus status;
    @ManyToOne
    private Customer customer;  // to specify the cardinalities
    @OneToMany(mappedBy = "bankAccount") // mappedBy indicates that the AccountOperation entity has a field named 'bankAccount
    List<AccountOperation> accountOperations; // Assuming AccountOperation is another entity that you have defined
}
