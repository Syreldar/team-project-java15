package org.project.entities.review.shopreview;

import org.project.entities.review.ReviewDTO;
import org.project.entities.shop.Shop;

import java.time.LocalDateTime;
import java.util.Objects;

public class ShopReviewDTO extends ReviewDTO {

    private Shop reviewedShop;

    public ShopReviewDTO() {}

    public ShopReviewDTO(float rating, String comment, LocalDateTime creationDate, LocalDateTime updateDate) {
        super(rating, comment, creationDate, updateDate);
    }

    public Shop getReviewedShop() {
        return reviewedShop;
    }

    public void setReviewedShop(Shop reviewedShop) {
        this.reviewedShop = reviewedShop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShopReviewDTO shopReview = (ShopReviewDTO) o;
        return Double.compare(getRating(), shopReview.getRating()) == 0 &&
                Objects.equals(reviewedShop, shopReview.reviewedShop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewedShop);
    }

    @Override
    public String toString() {
        return String.format("ShopReview [ReviewedShop: %s, Rating: %.1f, Comment: %s]",
                reviewedShop, getRating(), getComment());
    }
}
