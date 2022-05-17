package com.returners.movies.model;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

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
    private String[] actors;

    @Column
    private int rating;

    @Column
    private String title;

    @Column
    private int year;

    @OneToOne()
    @JoinColumn(name="certification_id")
    private Certification certification;


    @OneToOne()
    @JoinColumn(name="genre_id")
    private Genre genre;

}
