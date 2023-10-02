package org.project;

public class ShopReview extends Review {
    private final Shop reviewedShop;

    public ShopReview(Shop reviewedShop, Customer reviewer, float rating, String comment) {
        super(reviewer, rating, comment);
        this.reviewedShop = reviewedShop;
    }

    public Shop getReviewedShop() {
        return reviewedShop;
    }
}
