package org.project.entities.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.project.entities.customer.Customer;

import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "reviewer_id", referencedColumnName = "id", nullable = false)
    private Customer reviewer;

    @NotNull
    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private double rating;

    private String comment = "";

    @NotNull
    @Column(name = "creation_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate = LocalDateTime.now();

    @NotNull
    @Column(name = "update_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updateDate = LocalDateTime.now();

    public Review() {}

    public Review(Customer customer, double rating, String comment, LocalDateTime creationDate, LocalDateTime updateDate) {
        this.reviewer = customer;
        this.rating = rating;
        this.comment = comment;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
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
        return Double.compare(rating, review.rating) == 0 &&
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
