package org.project;

import java.util.List;

public class EntityFactory {
    private final Database database;
    private final Chart chart;

    public EntityFactory(Database database, Chart chart) {
        this.database = database;
        this.chart = chart;
    }

    public Product createProduct(Product product) {
        product.register(database);
        this.chart.addProduct(product);
        return product;
    }

    public List<Product> createProducts(List<Product> productList) {
        for (Product product : productList) {
            product.register(database);
            this.chart.addProduct(product);
        }
        return productList;
    }

    public Shop createShop(Shop shop) {
        shop.register(database);
        this.chart.addShop(shop);
        return shop;
    }

    public List<Shop> createShops(List<Shop> shopsList) {
        for (Shop shop : shopsList) {
            shop.register(database);
            this.chart.addShop(shop);
        }
        return shopsList;
    }

    public Customer createCustomer(Customer customer) {
        customer.register(database);
        this.chart.addCustomer(customer);
        return customer;
    }

    public List<Customer> createCustomers(List<Customer> customersList) {
        for (Customer customer : customersList) {
            customer.register(database);
            this.chart.addCustomer(customer);
        }
        return customersList;
    }
}
