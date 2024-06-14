package com.consis.test.edgar.service;

import com.consis.test.edgar.domain.Account;
import com.consis.test.edgar.domain.Person;
import com.consis.test.edgar.domain.TypeAccount;
import com.consis.test.edgar.domain.dto.AccountDto;
import com.consis.test.edgar.repository.AccountRepository;
import com.consis.test.edgar.repository.PersonRepository;
import com.consis.test.edgar.repository.TypeAccountRepository;
import com.consis.test.edgar.request.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private PersonRepository rperson;
    @Autowired
    private TypeAccountRepository rTypeAccount;

    @Override
    public Page<AccountDto> findAll(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Account> peoplePage = repository.findAll(pageable);

        return peoplePage.map(AccountDto::new);
    }

    @Override
    public AccountDto findByNumber(String number) {

        Account account = repository.findByAccountNumber(number);

        if (account != null) {
            return new AccountDto(account);
        } else {
            return null;
        }
    }

    @Override
    public boolean saveAccount(AccountRequest request) {

        Person person = rperson.findById(request.getPersonId()).orElseThrow(null);
        TypeAccount typeAccount = rTypeAccount.findById(request.getTypeAccountId()).orElseThrow(null);

        Account account = new Account();
        account.setAccountNumber(request.getAccountNumber());
        account.setAmount(request.getAmount());
        account.setTypeAccount(typeAccount);
        account.setPerson(person);
        account.setAccountNumber(request.getAccountNumber());

        try {
            repository.save(account);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
