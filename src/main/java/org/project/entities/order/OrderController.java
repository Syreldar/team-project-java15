package org.project.entities.order;

import jakarta.validation.Valid;
import org.project.helpers.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<APIResponse<Order>> add(@Valid @RequestBody OrderDTO orderDTO) {
        try {
            Order createdOrder = orderService.createOrder(orderDTO);
            return ResponseEntity.ok(
                    new APIResponse<>(createdOrder, "Order created successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to create Order."));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Order>> deleteById(@PathVariable Long id) {
        try {
            orderService.deleteById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(null, String.format("Order with ID %d deleted successfully.", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, String.format("Failed to delete Order with ID %d.", id)));
        }
    }

    @DeleteMapping
    public ResponseEntity<APIResponse<Void>> deleteAll() {
        try {
            orderService.deleteAll();
            return ResponseEntity.ok(
                    new APIResponse<>(null, "All Orders deleted successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to delete all Orders."));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Order>> findById(@PathVariable Long id) {
        try {
            Order order = orderService.findById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(order, String.format("Order with ID %d retrieved successfully.", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null, String.format("Order with ID %d not found.", id)));
        }
    }

    @GetMapping
    public ResponseEntity<APIResponse<Iterable<Order>>> findAll() {
        try {
            Iterable<Order> orders = orderService.getAllOrders();
            return ResponseEntity.ok(
                    new APIResponse<>(orders, "All Orders retrieved successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to get all Orders."));
        }
    }
}