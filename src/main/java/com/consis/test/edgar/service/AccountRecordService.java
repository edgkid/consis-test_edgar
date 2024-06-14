package com.consis.test.edgar.service;

import com.consis.test.edgar.domain.AccountRecord;
import com.consis.test.edgar.domain.dto.AccountDto;
import com.consis.test.edgar.domain.dto.AccountRecordDto;
import com.consis.test.edgar.request.AccountRecordRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AccountRecordService {

    public Page<AccountRecordDto> getAllAccountRecords(int pageNumber, int pageSize);

    List<AccountRecordDto> getAllByAccountNumber(String number);

    public boolean createAccountRecord(@RequestBody AccountRecordRequest request);
}
