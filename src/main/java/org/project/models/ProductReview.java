package org.project.models;

import org.project.database.Database;

//@Entity
public class ProductReview extends Review {
    private Product reviewedProduct;

    public ProductReview() {}

    public ProductReview(Product reviewedProduct, Customer reviewer, float rating, String comment) {
        super(reviewer, rating, comment);
        this.reviewedProduct = reviewedProduct;
    }

    public Product getReviewedProduct() {
        return reviewedProduct;
    }

    public void setReviewedProduct(Product reviewedProduct) {
        this.reviewedProduct = reviewedProduct;
    }

    @Override
    public void register(Database database) {
        database.registerProductReview(this);
    }
}
