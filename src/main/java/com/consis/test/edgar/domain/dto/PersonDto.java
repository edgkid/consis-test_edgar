package com.consis.test.edgar.domain.dto;

import com.consis.test.edgar.domain.Person;
import com.consis.test.edgar.domain.UserClient;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Setter
public class PersonDto {

    private Long id;
    private String dni;
    private String name;
    private String lastName;
    private String phone;
    private Timestamp birthday;
    private String address;

    private List<UserClient> usersInfo;

    public PersonDto(Person person) {
        this.id = person.getId();
        this.dni = person.getDni();
        this.name = person.getName();
        this.lastName = person.getLastName();
        this.phone = person.getPhone();
        this.birthday = person.getBirthday();
        this.address = person.getAddress();
        this.usersInfo = person.getUserInfo();
    }

    /*public Person getEntity(){

        Person person = new Person();

        person.setId(this.getId());
        person.setDni(this.getDni());
        person.setName(this.getName());
        person.setLastName(this.getLastName());
        person.setPhone(this.getPhone());
        person.setBirthday(this.getBirthday());
        person.setAddress(this.getAddress());
        person.setUserInfo(this.getUsersInfo());

        return person;
    }*/

}
