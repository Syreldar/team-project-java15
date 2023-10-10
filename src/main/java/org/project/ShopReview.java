package org.project;

public class ShopReview extends Review {
    private final Shop reviewedShop;

    public ShopReview(Shop reviewedShop, Customer reviewer, float rating, String comment) {
        super(reviewer, rating, comment);
        this.reviewedShop = reviewedShop;
    }

    @Override
    public void register(Database database, Chart chart) {
        database.registerShopReview(this);
        chart.addShopReview(this);
    }

    public Shop getReviewedShop() {
        return reviewedShop;
    }
}
