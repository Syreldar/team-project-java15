package org.project.entities.review.productreview;

import org.project.entities.product.Product;
import org.project.entities.review.ReviewDTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProductReviewDTO extends ReviewDTO {

    private Product reviewedProduct;

    public ProductReviewDTO() {}

    public ProductReviewDTO(float rating, String comment, LocalDateTime creationDate, LocalDateTime updateDate) {
        super(rating, comment, creationDate, updateDate);
    }

    public Product getReviewedProduct() {
        return reviewedProduct;
    }

    public void setReviewedProduct(Product reviewedProduct) {
        this.reviewedProduct = reviewedProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductReviewDTO productReview = (ProductReviewDTO) o;
        return Double.compare(getRating(), productReview.getRating()) == 0 &&
                Objects.equals(reviewedProduct, productReview.reviewedProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewedProduct);
    }

    @Override
    public String toString() {
        return String.format("ProductReview [ReviewedProduct: %s, Rating: %.1f, Comment: %s]",
                reviewedProduct, getRating(), getComment());
    }
}
