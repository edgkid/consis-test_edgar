package com.consis.test.edgar.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {

    private Long id;

    private String accountNumber;

    private double amount;

    private Long typeAccountId;

    private Long personId;
}
