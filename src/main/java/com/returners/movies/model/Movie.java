package com.returners.movies.model;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Arrays;
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

    @Type(type = "list-array")
    @Column(
            name = "actors",
            columnDefinition = "text[]"
    )
    private List<String> actors;

    @Column
    private int rating;

    @Column
    private String title;

    @Column
    private int year;

    @Column
    private int certification_id;

    @Column
    private int genre_id;


//    @Override
//    public String toString() {
//        return "Movie{" +
//                "id=" + id +
//                ", actors=" + Arrays.toString(actors) +
//                ", rating=" + rating +
//                ", title='" + title + '\'' +
//                ", year=" + year +
//                ", certification=" + certification +
//                ", genre=" + genre +
//                '}';
//    }
}
