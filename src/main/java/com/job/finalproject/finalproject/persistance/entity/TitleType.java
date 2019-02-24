package com.job.finalproject.finalproject.persistance.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "titletype")
public class TitleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false)
    private String type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_titletype",
            joinColumns = {@JoinColumn(name = "titletype_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")})
   private List<Movie> movieList=new ArrayList<>();

    @Override
    public String toString() {
        return "TitleType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleType titleType = (TitleType) o;
        return Objects.equals(id, titleType.id) &&
                Objects.equals(type, titleType.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type);
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
