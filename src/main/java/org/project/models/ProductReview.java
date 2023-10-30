package org.project.models;

import org.project.database.Database;

public class ProductReview extends Review {
    private final Product reviewedProduct;

    public ProductReview(Product reviewedProduct, Customer reviewer, float rating, String comment) {
        super(reviewer, rating, comment);
        this.reviewedProduct = reviewedProduct;
    }

    @Override
    public void register(Database database) {
        database.registerProductReview(this);
    }

    public Product getReviewedProduct() {
        return reviewedProduct;
    }
}
