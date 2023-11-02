package org.project.interfaces;

import java.util.List;

import org.project.models.Review;

public interface Reviewable<T extends Review> {
    List<T> getReviews();
    void addReview(T review);
    int getReviewCount();
    double getReviewsAverage();
}
