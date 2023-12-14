package org.project.entities.review.interfaces;

import java.util.List;

import org.project.entities.review.Review;

public interface Reviewable<T extends Review> {

    List<T> getReviews();

    void setReviews(List<T> reviews);

    void addReview(T review);

    void removeReview(T review);

    void addReviews(List<T> reviews);

    void removeReviews(List<T> reviews);

    void clearReviews();
}
