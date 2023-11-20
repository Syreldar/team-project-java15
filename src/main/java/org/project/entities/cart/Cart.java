package org.project.entities.cart;

import jakarta.persistence.*;
import org.project.entities.customer.Customer;
import org.project.entities.product.Product;
import org.project.entities.shop.Shop;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Product> items;
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Cart() {
    }

    public Cart(Long id, List<Product> items, Shop shop, Customer customer) {
        this.id = id;
        this.items = items;
        this.shop = shop;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double calculateTotalAmount() throws RuntimeException, IllegalArgumentException {
        if (items == null) {
            throw new IllegalArgumentException("La lista dei prodotti è nulla.");
        }

        if (items.isEmpty()) {
            throw new RuntimeException("Il carrello è vuoto.");
        }

        double totalAmount = 0.0;

        for (Product product : items) {
            if (product == null) {
                throw new IllegalArgumentException("Il carrello contiene un prodotto nullo.");
            }

            if (product.getPrice() == null) {
                throw new IllegalArgumentException("Il prezzo di un prodotto nel carrello è nullo.");
            }

            totalAmount += product.getPrice();
        }

        return totalAmount;
    }

    public void addProductToCart(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Il prodotto non può essere nullo.");
        }
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(product);
    }

    public int getTotalProductsInCart() {
        int totalQuantity = 0;
        for (Product product : this.items) {
            totalQuantity += product.getQuantity();
        }
        return totalQuantity;
    }
}
