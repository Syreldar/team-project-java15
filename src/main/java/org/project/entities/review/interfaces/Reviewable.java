package org.project.entities.review.interfaces;

import java.util.List;

import org.project.entities.review.Review;

public interface Reviewable<T extends Review> {
    List<T> getReviews();
    void addReview(T review);
}
