package org.project.entities.cart;

public class AddToCartDTO {

    private Long productId;
    private int quantity;

    public AddToCartDTO() {
    }

    public AddToCartDTO( Long productId, int quantity) {
        this.quantity = quantity;
        this.productId = productId;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}