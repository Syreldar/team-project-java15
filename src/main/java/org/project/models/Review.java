package org.project.models;

import org.project.interfaces.Storable;

import java.time.LocalDate;

//@MappedSuperclass
public abstract class Review implements Storable {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //@ManyToOne
    private Customer reviewer;
    private float rating;
    private String comment;
    //@JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;
    //@JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate updateDate;

    public Review() {}

    public Review(Customer customer, float rating, String comment) {
        this.reviewer = customer;
        this.rating = rating;
        this.comment = comment;
        this.creationDate = LocalDate.now();
        this.updateDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public String toString() {
        return String.format("Review [Reviewer: %s, Rating: %.1f, Comment: %s]", reviewer, rating, comment);
    }
}
