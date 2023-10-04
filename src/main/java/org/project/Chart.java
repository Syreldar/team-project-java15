package org.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Shop> getShops() {
        return Collections.unmodifiableList(shops);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }

    public List<Review> getReviews() {
        return Collections.unmodifiableList(reviews);
    }

    public void addShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("The shop cannot be null");
        }

        shops.add(shop);
        System.out.printf("Chart: Shop %s by %s added!%n", shop.getName(), shop.getOwnerName());
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("The product cannot be null");
        }

        products.add(product);
        System.out.printf("Chart: Product %s added!%n", product.getName());
    }

    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("The customer cannot be null");
        }

        customers.add(customer);
        System.out.printf("Chart: Customer %s added!%n", customer.getFullName());
    }

    public void addReview(Review review) {
        if (review == null) {
            throw new IllegalArgumentException("The review cannot be null");
        }

        reviews.add(review);
        System.out.printf("Chart: Review (%s, %.2f) added!%n", review.getComment(), review.getRating());
    }

    public List<Shop> getShopsBySells() {
        shops.sort((shop1, shop2) -> Double.compare(shop2.getTotalSales(), shop1.getTotalSales()));
        return Collections.unmodifiableList(shops);
    }

    public List<Shop> getShopsByGains() {
        shops.sort((n1, n2) -> Double.compare(n2.getTotalGains(), n1.getTotalGains()));
        return Collections.unmodifiableList(shops);
    }

    public List<Shop> getShopsByCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("The category cannot be null");
        }
        return shops.stream()
                .filter(s -> category.equals(s.getMostSoldCategory()))
                .sorted(Comparator.comparingDouble(Shop::getTotalGains).reversed())
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Chart [\n");
        for (Shop s : shops) {
            sb.append("  ").append(s).append(",\n");
        }
        sb.append("]");
        return sb.toString();
    }
}
