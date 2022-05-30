package com.returners.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.returners.movies.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @NotBlank(message = Constants.USERNAME_CANNOT_BE_BLANK)
    private String userName;

    @Column(name="password")
    @NotBlank(message = Constants.PASSWORD_CANNOT_BE_BLANK)
    private String password;

    @Column(name="age")
    @Max(100)
    @Positive
    @NotNull(message = Constants.AGE_REQUIRED)
    private int age;

    @Column(name="email")
    @Email(message = Constants.EMAIL_VALID)
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(name="name", nullable=false)
    @NotBlank(message = Constants.NAME_MANDATORY)
    private String name;

    @Column(name="phone_number")
    @Pattern(regexp="^(0|[\\+]44|)\\d{10}$", message = Constants.MOBILE_VALID)
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "favorite_movies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> favouriteMovies = new HashSet<>();

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void addFavourite(Movie movie) {
        favouriteMovies.add(movie);
    }

    public void deleteFavourite(Movie movie) {
        favouriteMovies.remove(movie);
    }
}