package org.project.review.interfaces;

import java.util.List;

import org.project.review.Review;

public interface Reviewable<T extends Review> {
    List<T> getReviews();
    void addReview(T review);
    int getReviewCount();
    double getReviewsAverage();
}
