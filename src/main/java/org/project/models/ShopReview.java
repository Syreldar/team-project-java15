package org.project.models;

import org.project.database.Database;

public class ShopReview extends Review {
    private final Shop reviewedShop;

    public ShopReview(Shop reviewedShop, Customer reviewer, float rating, String comment) {
        super(reviewer, rating, comment);
        this.reviewedShop = reviewedShop;
    }

    @Override
    public void register(Database database) {
        database.registerShopReview(this);
    }

    public Shop getReviewedShop() {
        return reviewedShop;
    }
}
