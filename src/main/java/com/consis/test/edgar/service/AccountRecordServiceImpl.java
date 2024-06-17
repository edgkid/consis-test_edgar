package com.consis.test.edgar.service;

import com.consis.test.edgar.domain.Account;
import com.consis.test.edgar.domain.AccountRecord;
import com.consis.test.edgar.domain.AccountRecordType;
import com.consis.test.edgar.domain.dto.AccountRecordDto;
import com.consis.test.edgar.domain.dto.AccountRecordReportDto;
import com.consis.test.edgar.repository.AccountRecordRepository;
import com.consis.test.edgar.repository.AccountRecordTypeRepository;
import com.consis.test.edgar.repository.AccountRepository;
import com.consis.test.edgar.request.AccountRecordReportRequest;
import com.consis.test.edgar.request.AccountRecordRequest;
import com.consis.test.edgar.util.MessageUtil;
import com.consis.test.edgar.util.ValidateUtilRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.management.Query.value;

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
    public ValidateUtilRecord createAccountRecord(AccountRecordRequest request) {

        ValidateUtilRecord validateUtilRecord = new ValidateUtilRecord(true, MessageUtil.MSG_OPERATION_SUCCESS_DESCRIPTON);

        Account account = raccount.findById(request.getAccountId()).orElseThrow(null);
        AccountRecordType recordType = rtype.findById(request.getAccountRecordTypeId()).orElseThrow(null);

        if (!validateTransaction(request, recordType)){

            validateUtilRecord.setReason(MessageUtil.MSG_OPERATION_TRANSACTION_INVALID);
            validateUtilRecord.setValue(false);
            System.out.println(validateUtilRecord.getReason());
            return validateUtilRecord;
        }

        if(!validateLimit(request, recordType)){
            validateUtilRecord.setReason(MessageUtil.MSG_OPERATION_TRANSACTION_INVALID_BY_AMOUNT);
            validateUtilRecord.setValue(false);
            System.out.println(validateUtilRecord.getReason());
            return validateUtilRecord;
        }

        AccountRecord accountRecord = new AccountRecord();
        accountRecord.setRecordDate(request.getRecordDate());
        accountRecord.setAmount(request.getAmount());
        accountRecord.setAmountValue(request.getAmountValue());
        accountRecord.setAccount(account);
        accountRecord.setAccountRecordType(recordType);

        try {
            repository.save(accountRecord);
            System.out.println(validateUtilRecord.getReason());
            return validateUtilRecord;
        } catch (Exception e) {
            e.printStackTrace();
            validateUtilRecord.setReason(MessageUtil.MSG_OPERATION_ERROR_DESCRIPTION);
            validateUtilRecord.setValue(false);
            System.out.println(validateUtilRecord.getReason());
            return validateUtilRecord;

        }
    }

    @Override
    public List<AccountRecord> getReportByDate(AccountRecordReportRequest request) {

        List<AccountRecord> accountRecords = repository.findByRecordDateBetween(request.getInit(), request.getEnd());

        return accountRecords;
    }


    private boolean validateTransaction(AccountRecordRequest request, AccountRecordType recordType){

        boolean value = false;

        if(recordType.getId() == 2){
            System.out.println("Validacion A");
            System.out.println(request.getAmountValue());
            System.out.print(request.getAmount());
            value=(request.getAmountValue() < request.getAmount())?true:false;

        }else{
            value = true;
        }

        return value;
    }

    private boolean validateLimit(AccountRecordRequest request, AccountRecordType recordType){

        double todayMovements = 0;
        boolean value = false;
        todayMovements = repository.findTotalAmountByAccountId(request.getAccountId());

        if(recordType.getName().equals("Debito")){
            System.out.println("Validacion B");
            value =(request.getAmount() <= 1000 && todayMovements < 1000)?true:false;
        }else{
            value = true;
        }

        return value;
    }
}
