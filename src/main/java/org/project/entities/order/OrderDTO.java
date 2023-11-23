package org.project.entities.order;

import jakarta.validation.constraints.NotNull;
import org.project.entities.product.Product;

import java.util.List;

public class OrderDTO {
    private Long id;

    @NotNull
    private Long customerId;
    private OrderStatus orderStatus;
    @NotNull
    private List<Product> products;

    public OrderDTO(Long id, Long customerId, List<Product> products) {
        this.id = id;
        this.customerId = customerId;
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public OrderDTO() {
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }



    @Override
    public String toString() {
        return String.format("OrderId: %d, CustomerId: %d, Products: %s", id, customerId, products);
    }
}