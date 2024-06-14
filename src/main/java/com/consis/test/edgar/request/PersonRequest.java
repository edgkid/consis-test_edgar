package com.consis.test.edgar.request;

import com.consis.test.edgar.domain.Account;
import com.consis.test.edgar.domain.UserClient;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class PersonRequest
{
    private Long id;
    private String dni;
    private String name;
    private String lastName;
    private String phone;
    private Timestamp birthday;
    private String address;
    private List<UserClient> userInfo;
    private List<Account> accounts;
}
