package com.consis.test.edgar.service;

import com.consis.test.edgar.domain.Account;
import com.consis.test.edgar.domain.AccountRecord;
import com.consis.test.edgar.domain.AccountRecordType;
import com.consis.test.edgar.domain.dto.AccountRecordDto;
import com.consis.test.edgar.repository.AccountRecordRepository;
import com.consis.test.edgar.repository.AccountRecordTypeRepository;
import com.consis.test.edgar.repository.AccountRepository;
import com.consis.test.edgar.request.AccountRecordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountRecordServiceImpl implements AccountRecordService {

    @Autowired
    private AccountRecordRepository repository;
    @Autowired
    private AccountRepository raccount;
    @Autowired
    private AccountRecordTypeRepository rtype;
    @Override
    public Page<AccountRecordDto> getAllAccountRecords(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<AccountRecord> peoplePage = repository.findAll(pageable);

        return peoplePage.map(AccountRecordDto::new);
    }

    @Override
    public List<AccountRecordDto> getAllByAccountNumber(String number) {

        List<AccountRecordDto> records = new ArrayList<>();
        List<AccountRecord> accoundRecords = null;
        Account account = raccount.findByAccountNumber(number);

        if (account != null){

             accoundRecords = repository.findAccountRecordsByAccountId(account.getId());
            for (AccountRecord record : accoundRecords) {
                AccountRecordDto dto = new AccountRecordDto(record);
                records.add(dto);
            }

        }

        return records;
    }

    @Override
    public boolean createAccountRecord(AccountRecordRequest request) {

        Account account = raccount.findById(request.getAccountId()).orElseThrow(null);
        AccountRecordType recordType = rtype.findById(request.getAccountRecordTypeId()).orElseThrow(null);

        AccountRecord accountRecord = new AccountRecord();
        accountRecord.setRecordDate(request.getRecordDate());
        accountRecord.setAmount(request.getAmount());
        accountRecord.setAmountValue(request.getAmountValue());
        accountRecord.setAccount(account);
        accountRecord.setAccountRecordType(recordType);

        try {
            repository.save(accountRecord);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
