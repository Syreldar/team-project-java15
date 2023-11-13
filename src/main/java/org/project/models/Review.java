package org.project.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public abstract class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reviewer_id", referencedColumnName = "id")
    private Customer reviewer;

    private float rating;
    private String comment;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate updateDate;

    public Review() {}

    public Review(Customer customer, float rating, String comment) {
        this.reviewer = customer;
        this.rating = rating;
        this.comment = comment;
        this.creationDate = LocalDate.now();
        this.updateDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Customer reviewer) {
        this.reviewer = reviewer;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Review review = (Review) o;
        return Float.compare(review.rating, rating) == 0 &&
            Objects.equals(reviewer, review.reviewer) &&
            Objects.equals(comment, review.comment) &&
            Objects.equals(creationDate, review.creationDate) &&
            Objects.equals(updateDate, review.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewer, rating, comment, creationDate, updateDate);
    }

    @Override
    public String toString() {
        return String.format("Review [Reviewer: %s, Rating: %.1f, Comment: %s]", reviewer, rating, comment);
    }
}
