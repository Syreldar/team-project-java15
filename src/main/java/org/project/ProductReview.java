package org.project;

public class ProductReview extends Review {
    private final Product reviewedProduct;

    public ProductReview(Product reviewedProduct, Customer reviewer, float rating, String comment) {
        super(reviewer, rating, comment);
        this.reviewedProduct = reviewedProduct;
    }

    @Override
    public void register(Database database, Chart chart) {
        database.registerProductReview(this);
        chart.addProductReview(this);
    }

    public Product getReviewedProduct() {
        return reviewedProduct;
    }
}
