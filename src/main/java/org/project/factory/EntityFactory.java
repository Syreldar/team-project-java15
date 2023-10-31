package org.project.factory;

import org.project.database.Database;
import org.project.models.Customer;
import org.project.models.Product;
import org.project.models.Shop;

import java.util.List;

public record EntityFactory(Database database) {
    public Product createProduct(Product product) {
        product.register(database);
        return product;
    }

    public List<Product> createProducts(List<Product> productList) {
        for (Product product : productList) {
            product.register(database);
        }
        return productList;
    }

    public Shop createShop(Shop shop) {
        shop.register(database);
        return shop;
    }

    public List<Shop> createShops(List<Shop> shopsList) {
        for (Shop shop : shopsList) {
            shop.register(database);
        }
        return shopsList;
    }

    public Customer createCustomer(Customer customer) {
        customer.register(database);
        return customer;
    }

    public List<Customer> createCustomers(List<Customer> customersList) {
        for (Customer customer : customersList) {
            customer.register(database);
        }
        return customersList;
    }
}
