package com.consis.test.edgar.service;

import com.consis.test.edgar.domain.dto.AccountDto;
import com.consis.test.edgar.request.AccountRequest;
import org.springframework.data.domain.Page;

public interface AccountService {

    Page<AccountDto> findAll(int pageNumber, int pageSize);

    AccountDto findByNumber(String number);

    boolean saveAccount(AccountRequest request);

}
