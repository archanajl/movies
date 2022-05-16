package com.returners.movies.model;

import java.util.List;

public class SearchCriteria {


    private Long id;

    private List<String> actors;

    private int rating;


    private String title;


    private int year;


    private Long certificationId;

    private Long genreId;

    public Long getId() {
        return id;
    }

    public List<String> getActors() {
        return actors;
    }

    public int getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Long getCertificationId() {
        return certificationId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public SearchCriteria() {
    }

    public SearchCriteria(Long id, List<String> actors, int rating, String title, int year, Long certificationId, Long genreId) {
        this.id = id;
        this.actors = actors;
        this.rating = rating;
        this.title = title;
        this.year = year;
        this.certificationId = certificationId;
        this.genreId = genreId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCertificationId(Long certificationId) {
        this.certificationId = certificationId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
}
