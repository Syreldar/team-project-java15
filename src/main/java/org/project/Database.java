package org.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {
    List<Shop> shops;
    List<Customer> customers;
    HashMap<Shop, List<Product>> sales;

    public Database() {
        this.shops = new ArrayList<Shop>();
    }

    public void addShop(Shop shop) {
        shops.add(shop);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addSales(Shop sellerShop, Product product) {
        if (!sales.containsKey(sellerShop)) {
            sales.put(sellerShop, new ArrayList<Product>());
        }
        sales.get(sellerShop).add(product);
    }
}
