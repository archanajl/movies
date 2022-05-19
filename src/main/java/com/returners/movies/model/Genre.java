package com.returners.movies.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="Genre")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Genre {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(name="name", nullable=false)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String name;

}
