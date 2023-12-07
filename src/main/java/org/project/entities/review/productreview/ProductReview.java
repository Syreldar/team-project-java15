package org.project.entities.review.productreview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.project.entities.customer.Customer;
import org.project.entities.product.Product;
import org.project.entities.review.Review;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "product_reviews")
public class ProductReview extends Review {
    @NotNull
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product reviewedProduct;

    public ProductReview() {}

    public ProductReview(Product reviewedProduct, Customer reviewer, double rating, String comment, LocalDateTime creationDate, LocalDateTime updateDate) {
        super(reviewer, rating, comment, creationDate, updateDate);
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
