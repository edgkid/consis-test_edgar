package com.consis.test.edgar.domain.dto;

import com.consis.test.edgar.domain.Account;
import com.consis.test.edgar.domain.AccountRecordType;

import java.util.Date;

public class AccountRecordReportDto {

    private Long id;

    private Date recordDate;

    private Double amount;

    private Double amountValue;

    private Long accountRecordTypeId;

    private Long accountId;
}
