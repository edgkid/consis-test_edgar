package com.consis.test.edgar.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "account_record")
public class AccountRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "record_date")
    private Date recordDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "amount_value")
    private Double amountValue;


    @ManyToOne
    @JoinColumn(name = "account_record_type_id")
    private AccountRecordType accountRecordType;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
