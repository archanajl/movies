package com.returners.movies.model;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

@Table(name="movie")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class Movie {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(columnDefinition = "text[]")
    @Type(type = "com.returners.movies.util.CustomStringArrayType")
    private @NotEmpty(message = "Movie actors' names are mandatory!") String[] actors;

    @Column
    private int rating;

    @Column
    @NotBlank(message = "Movie title is mandatory!")
    private String title;

    @Column
    @NotNull(message = "Movie year is mandatory!")
    private int year;

    @OneToOne()
    @JoinColumn(name="certification_id")
    @NotNull(message = "Certification id is mandatory!")
    private Certification certification;

    @OneToOne()
    @JoinColumn(name="genre_id")
    @NotNull(message = "Genre id is mandatory!")
    private Genre genre;

}
