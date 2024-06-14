package com.consis.test.edgar.request;

import com.consis.test.edgar.domain.Account;
import com.consis.test.edgar.domain.AccountRecordType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccountRecordRequest {

    private Long id;
    private Date recordDate;
    private Double amount;
    private Double amountValue;
    private Long accountRecordTypeId;
    private Long accountId;
}
