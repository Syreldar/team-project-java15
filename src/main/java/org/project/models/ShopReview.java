package org.project.models;

import org.project.database.Database;

public class ShopReview extends Review {
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
    public void register(Database database) {
        database.registerShopReview(this);
    }
}
