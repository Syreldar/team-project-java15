package org.project.models;

import jakarta.persistence.*;
import org.project.database.Database;
import org.project.charts.Chart;
@Entity
@Table(name = "ProductReview", schema = "newdb" )
public class ProductReview extends Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "reviewedProductId")
    private Product reviewedProduct;

    public ProductReview(Product reviewedProduct, Customer reviewer, float rating, String comment) {
        // Chiama il costruttore della classe madre (Review) con reviewer, rating e comment
        super(reviewer, rating, comment);
        this.reviewedProduct = reviewedProduct;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
