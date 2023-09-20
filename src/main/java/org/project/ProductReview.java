package org.project;

public class ProductReview extends Review {
    private final Product reviewedProduct;

    public ProductReview(Product reviewedProduct, Customer reviewer, float rating, String comment) {
        super(reviewer, rating, comment);
        this.reviewedProduct = reviewedProduct;
    }

    public Product getReviewedProduct() {
        return reviewedProduct;
    }
}
