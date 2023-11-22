package org.project.entities.cart;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.project.helpers.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.project.entities.product.ProductDTO;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PutMapping("/add")
    public ResponseEntity<APIResponse<Cart>> addProductToCart(
            @RequestParam Long cartId,
            @Valid @RequestBody ProductDTO productDTO)
    {
        try {
            cartService.addProductToCart(cartId, productDTO.getId());

            return ResponseEntity.ok(
                    new APIResponse<>(null, "Product added to cart successfully."));
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null, "Failed to add product to cart."));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Cart>> update(
            @PathVariable Long id,
            @Valid @RequestBody CartDTO cart)
    {
        try {
            Cart updatedCart = cartService.update(id, cart);
            return ResponseEntity.ok(
                    new APIResponse<>(updatedCart,
                            String.format("Cart with ID %d updated successfully.", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            String.format("Failed to update Cart with ID %d.", id)));
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<APIResponse<Cart>> deleteById(@PathVariable Long id) {
        try {
            cartService.deleteById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(null,
                            String.format("Cart with ID %d deleted successfully.", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null,
                            String.format("Failed to delete Cart with ID %d.", id)));
        }
    }

    @DeleteMapping
    public ResponseEntity<APIResponse<Iterable<Cart>>> deleteAll() {
        try {
            cartService.deleteAll();
            return ResponseEntity.ok(
                    new APIResponse<>(null, "All Carts deleted successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to delete all Carts."));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponse<Cart>> findById(@PathVariable Long id) {
        try {
            Cart customer = cartService.findById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(customer,
                            String.format("Cart with ID %d retrieved successfully.", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null,
                            String.format("Cart with ID %d not found.", id)));
        }
    }
}
