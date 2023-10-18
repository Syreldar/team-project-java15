package org.project.models;

import org.project.interfaces.Storable;

public abstract class Review implements Storable {
    private final Customer reviewer;
    private final float rating;
    private final String comment;

    public Review(Customer customer, float rating, String comment) {
        this.reviewer = customer;
        this.rating = rating;
        this.comment = comment;
    }

    public Customer getReviewer() {
        return reviewer;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return String.format("Review [Reviewer: %s, Rating: %.1f, Comment: %s]", reviewer, rating, comment);
    }
}
