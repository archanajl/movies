package com.returners.movies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

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
    @NotBlank(message = "Username cannot be blank")
    private String userName;

    @Column(name="password")
    @NotBlank(message = "password cannot be blank")
    private String password;

    @Column(name="age")
    @Max(100)
    @Positive
    @NotNull(message = "Age is required")
    private int age;

    @Column(name="email")
    @Email(message = "Please enter valid email")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(name="name", nullable=false)
    @NotBlank(message = "Please enter your Name")
    private String name;

    @Column(name="phone_number")
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Mobile number is invalid")
    private String phoneNumber;

    public int getAge() {
        return age;
    }
}