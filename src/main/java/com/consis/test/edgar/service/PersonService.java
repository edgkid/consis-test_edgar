package com.consis.test.edgar.service;

import com.consis.test.edgar.domain.dto.AccountDto;
import com.consis.test.edgar.domain.dto.PersonDto;
import com.consis.test.edgar.request.AccountRequest;
import com.consis.test.edgar.request.PersonRequest;
import org.springframework.data.domain.Page;

public interface PersonService {

    Page<PersonDto> findAll(int pageNumber, int pageSize);

    PersonDto findById(Long id);

    boolean savePerson(PersonRequest request);

    boolean updatePerson(PersonRequest request, Long id);

    public boolean deletePerson(Long id);
}
