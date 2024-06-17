package com.consis.test.edgar.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "type_account_id")
    @JsonBackReference("typeAccount")
    private TypeAccount typeAccount;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference("person")
    private Person person;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AccountRecord> accounts;

}
