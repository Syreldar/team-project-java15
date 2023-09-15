package org.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chart {
    private final List<Shop> shops;
    private final List<Product> products;
    private final List<Customer> customers;
    private final List<Review> reviews;

    public Chart() {
        this.shops = new ArrayList<>();
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public void addShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("The shop cannot be null");
        }

        this.shops.add(shop);
        System.out.printf("Chart: Shop %s by %s added!%n", shop.getName(), shop.getOwnerName());
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("The product cannot be null");
        }

        this.products.add(product);
        System.out.printf("Chart: Product %s added!%n", product.getName());
    }

    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("The customer cannot be null");
        }

        this.customers.add(customer);
        System.out.printf("Chart: Customer %s added!%n", customer.getFullName());
    }

    public void addReview(Review review) {
        if (review == null) {
            throw new IllegalArgumentException("The review cannot be null");
        }

        this.reviews.add(review);
        System.out.printf("Chart: Review (%s, %.2f) added!%n", review.getComment(), review.getRating());
    }

    public List<Shop> getShopsBySells() {
        this.shops.sort((shop1, shop2) -> Double.compare(shop2.getTotalSales(), shop1.getTotalSales()));
        return Collections.unmodifiableList(this.shops);
    }

    public List<Shop> getShopsByGains() {
        this.shops.sort((n1, n2) -> n2.getTotalGains().compareTo(n1.getTotalGains()));
        return Collections.unmodifiableList(this.shops);
    }

    public List<Shop> getShopsByCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("The category cannot be null");
        }

        List<Shop> filteredShops = new ArrayList<>();
        for (Shop shop : this.shops) {
            if (category.equals(shop.getMostSoldCategory())) {
                filteredShops.add(shop);
            }
        }

        filteredShops.sort((n1, n2) -> n2.getTotalGains().compareTo(n1.getTotalGains()));
        return Collections.unmodifiableList(filteredShops);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Chart [\n");
        for (Shop shop : this.shops) {
            sb.append("  ").append(shop.toString()).append(",\n");
        }
        sb.append("]");
        return sb.toString();
    }
}
