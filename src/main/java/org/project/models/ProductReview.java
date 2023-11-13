package org.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "product_reviews")
public class ProductReview extends Review {
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductReview productReview = (ProductReview) o;
        return Float.compare(productReview.getRating(), getRating()) == 0 &&
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
