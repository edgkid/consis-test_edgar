package com.consis.test.edgar.domain.dto;

import com.consis.test.edgar.domain.Account;
import com.consis.test.edgar.domain.AccountRecord;
import com.consis.test.edgar.domain.AccountRecordType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AccountRecordDto {

    private Long id;

    private Date recordDate;

    private Double amount;

    private Double amountValue;

    private AccountRecordType accountRecordType;

    private Account account;

    public AccountRecordDto(AccountRecord accountRecord) {
        this.id = accountRecord.getId();
        this.recordDate = accountRecord.getRecordDate();
        this.amount = accountRecord.getAmount();
        this.amountValue = accountRecord.getAmountValue();
        this.accountRecordType = accountRecord.getAccountRecordType();
        this.account = accountRecord.getAccount();
    }
}
