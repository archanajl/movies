package com.returners.movies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="\"user\"")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(name="username", nullable=false)
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="age")
    private int age;

    @Column(name="email")
    private String email;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="phone_number")
    private String phoneNumber;

}