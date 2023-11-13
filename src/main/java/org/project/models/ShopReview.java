package org.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "shop_reviews")
public class ShopReview extends Review {
    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop reviewedShop;

    public ShopReview() {}

    public ShopReview(Shop reviewedShop, Customer reviewer, float rating, String comment) {
        super(reviewer, rating, comment);
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
        return Float.compare(shopReview.getRating(), getRating()) == 0 &&
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
