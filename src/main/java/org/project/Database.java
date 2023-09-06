import org.project.Customer;
import org.project.Shop;
import org.project.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
