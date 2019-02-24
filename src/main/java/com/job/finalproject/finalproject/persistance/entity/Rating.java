package com.job.finalproject.finalproject.persistance.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long movieid;
    private Long reviewerid;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movieid",insertable = false,updatable = false)
    private Movie movie;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reviewerid",insertable = false,updatable = false)
    private Reviewer reviewer;

    private int reviewerating;

    private Date crated;


    public Date getCrated() {

        return crated;
    }

    public void setCrated(Date crated) {
        this.crated = crated;
    }

    public int getReviewerating() {
        return reviewerating;
    }

    public void setReviewerating(int reviewerating) {
        this.reviewerating = reviewerating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }



    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public Long getMovieid() {
        return movieid;
    }

    public void setMovieid(Long movieid) {
        this.movieid = movieid;
    }

    public Long getReviewerid() {
        return reviewerid;
    }

    public void setReviewerid(Long reviewerid) {
        this.reviewerid = reviewerid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return reviewerating == rating.reviewerating &&
                Objects.equals(id, rating.id) &&
                Objects.equals(movieid, rating.movieid) &&
                Objects.equals(reviewerid, rating.reviewerid) &&
                Objects.equals(movie, rating.movie) &&
                Objects.equals(reviewer, rating.reviewer) &&
                Objects.equals(crated, rating.crated);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, movieid, reviewerid, movie, reviewer, reviewerating, crated);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", movieid=" + movieid +
                ", reviewerid=" + reviewerid +
                ", movie=" + movie +
                ", reviewer=" + reviewer +
                ", reviewerating=" + reviewerating +
                ", crated=" + crated +
                '}';
    }
}
