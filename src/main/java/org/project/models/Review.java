package org.project.models;

import org.project.interfaces.Storable;

public abstract class Review implements Storable {
    private int id;
    private Customer reviewer;
    private float rating;
    private String comment;

    public Review() {}

    public Review(Customer customer, float rating, String comment) {
        this.reviewer = customer;
        this.rating = rating;
        this.comment = comment;
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

    @Override
    public String toString() {
        return String.format("Review [Reviewer: %s, Rating: %.1f, Comment: %s]", reviewer, rating, comment);
    }
}
