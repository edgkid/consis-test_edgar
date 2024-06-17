package com.consis.test.edgar.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_client")
public class UserClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private int status;

    @Column(name = "password")
    private int password;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference("person")
    private Person person;
}
