package com.returners.movies.model;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column
    @Type(type = "com.returners.movies.util.CustomStringArrayType")
    //@NotEmpty(message = "Movie actors' names are mandatory!")
    private String[] actors;

    @Column
    private int rating;

    @Column
    @NotBlank(message = "Movie title is mandatory!")
    private String title;

    @Column
    @NotNull(message = "Movie year is mandatory!")
    private int year;

    @OneToOne()
    @JoinColumn(name="certificationId",
    referencedColumnName = "id",
    insertable = false,updatable = false)
    @NotNull(message = "Certification id is mandatory!")
    private Certification certification;


    @OneToOne()
    @JoinColumn(name="genreId",
            referencedColumnName = "id",
            insertable = false,updatable = false)
    @NotNull(message = "Genre id is mandatory!")
    private Genre genre;


}
