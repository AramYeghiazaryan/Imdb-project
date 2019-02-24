package com.job.finalproject.finalproject.persistance.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false, unique = true)
    private String fullname;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_director",
            joinColumns = {@JoinColumn(name = "director_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")})
    private List<Movie> movieList=new ArrayList<Movie>();

    public  String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(id, director.id) &&
                Objects.equals(fullname, director.fullname) &&
                Objects.equals(movieList, director.movieList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fullname, movieList);
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", fullname='" + fullname + '\''+
                '}';
    }
}
