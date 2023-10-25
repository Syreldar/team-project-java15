package org.project.models;

import jakarta.persistence.*;
import org.project.database.Database;
import org.project.charts.Chart;
@Entity
@Table(name = "ShopReview", schema = "newdb" )
public class ShopReview extends Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
