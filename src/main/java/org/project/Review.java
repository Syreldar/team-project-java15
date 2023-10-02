package org.project;

public class Review implements Storable{
    private final Customer reviewer;
    private final float rating;
    private final String comment;

    public Review(Customer customer, float rating, String comment) {
        this.reviewer = customer;
        this.rating = rating;
        this.comment = comment;
    }

    public Customer getReviewer() {
        return reviewer;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public void register(Database database, Chart chart) {
        database.addReview(this);
        chart.addReview(this);
    }
}
