package com.job.finalproject.finalproject.persistance.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String code;
    private String title;
    private Date releasdate;
    @Column(unique = true)
    private String url;
    private int runtime;
    private BigDecimal imdbrating;
    private int numvotes;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_titletype",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "titletype_id")})
    private List<TitleType> titleTypeList = new ArrayList<>();

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Rating> ratingList = new ArrayList<Rating>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_genre",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private List<Genre> genres = new ArrayList<Genre>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_director",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "director_id")})
    private List<Director> directorList = new ArrayList<Director>();


    public List<TitleType> getTitleTypeList() {
        return titleTypeList;
    }

    public void setTitleTypeList(List<TitleType> titleTypeList) {
        this.titleTypeList = titleTypeList;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public BigDecimal getImdbrating() {
        return imdbrating;
    }

    public void setImdbrating(BigDecimal imdbrating) {
        this.imdbrating = imdbrating;
    }

    public int getNumvotes() {
        return numvotes;
    }

    public void setNumvotes(int numvotes) {
        this.numvotes = numvotes;
    }

    public List<Director> getDirectorList() {
        return directorList;
    }

    public void setDirectorList(List<Director> directorList) {
        this.directorList = directorList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleasdate() {
        return releasdate;
    }

    public void setReleasdate(Date releasdate) {
        this.releasdate = releasdate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return runtime == movie.runtime &&
                numvotes == movie.numvotes &&
                Objects.equals(id, movie.id) &&
                Objects.equals(code, movie.code) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(releasdate, movie.releasdate) &&
                Objects.equals(url, movie.url) &&
                Objects.equals(imdbrating, movie.imdbrating);

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, code, title, releasdate, url, runtime, imdbrating, numvotes, genres);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", releasdate=" + releasdate +
                ", url='" + url + '\'' +
                ", runtime=" + runtime +
                ", imdbrating=" + imdbrating +
                ", numvotes=" + numvotes +
                '}';
    }
}

