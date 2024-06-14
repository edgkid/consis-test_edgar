package com.consis.test.edgar.domain.dto;

import com.consis.test.edgar.domain.Account;
import com.consis.test.edgar.domain.Person;
import com.consis.test.edgar.domain.TypeAccount;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AccountDto {

    private Long id;

    private String accountNumber;

    private double amount;

    //private int typeAccountId;
    private TypeAccount typeAccount;

    private Person person;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.accountNumber = account.getAccountNumber();
        this.amount = account.getAmount();
        //this.typeAccountId = account.getTypeAccountId();
        this.typeAccount = account.getTypeAccount();
        this.person = account.getPerson();
    }
}
