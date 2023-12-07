package org.project.entities.review.shopreview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.project.entities.customer.Customer;
import org.project.entities.review.Review;
import org.project.entities.shop.Shop;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shop_reviews")
public class ShopReview extends Review {
    @NotNull
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop reviewedShop;

    public ShopReview() {}

    public ShopReview(Shop reviewedShop, Customer reviewer, double rating, String comment, LocalDateTime creationDate, LocalDateTime updateDate) {
        super(reviewer, rating, comment, creationDate, updateDate);
        this.reviewedShop = reviewedShop;
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

        ShopReview shopReview = (ShopReview) o;
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
