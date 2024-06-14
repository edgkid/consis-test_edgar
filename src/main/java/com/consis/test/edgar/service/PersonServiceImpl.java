package com.consis.test.edgar.service;

import com.consis.test.edgar.domain.Person;
import com.consis.test.edgar.domain.UserClient;
import com.consis.test.edgar.domain.dto.PersonDto;
import com.consis.test.edgar.repository.PersonRepository;
import com.consis.test.edgar.request.AccountRequest;
import com.consis.test.edgar.request.PersonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository repository;
    @Override
    public Page<PersonDto> findAll(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Person> peoplePage = repository.findAll(pageable);

        return peoplePage.map(PersonDto::new);
    }

    @Override
    public PersonDto findById(Long id) {

        Person person = repository.findById(id).orElse(null);

        if (person != null) {
            return new PersonDto(person);
        } else {
            return null;
        }
    }

    @Override
    public boolean savePerson(PersonRequest request) {

        Person person = new Person();
        UserClient userInfo = new UserClient();
        List<UserClient> listUserInfo = new ArrayList<>();

        person.setId(request.getId());
        person.setDni(request.getDni());
        person.setName(request.getName());
        person.setLastName(request.getLastName());
        person.setBirthday(request.getBirthday());
        person.setPhone(request.getPhone());
        person.setAddress(request.getAddress());

        userInfo.setPassword(request.getUserInfo().get(0).getPassword());
        userInfo.setStatus(request.getUserInfo().get(0).getStatus());
        userInfo.setPerson(person);
        listUserInfo.add(userInfo);
        person.setUserInfo(listUserInfo);

        try {
            repository.save(person);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePerson(PersonRequest request, Long id) {

        boolean value = false;

        Person personUpdated = repository.findById(id)
                .map(p -> {
                    p.setName(request.getName());
                    p.setLastName(request.getLastName());
                    p.setDni(request.getDni());
                    p.setAddress(request.getAddress());
                    p.setPhone(request.getPhone());
                    p.setBirthday(request.getBirthday());
                    p.setUserInfo(request.getUserInfo());
                    return repository.save(p);
                })
                .orElse(null);

        value =(personUpdated != null)? true: false;

        return value;
    }

    @Override
    public boolean deletePerson(Long id) {

        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
