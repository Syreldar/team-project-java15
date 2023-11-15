package org.project.controllers;

import org.project.models.APIResponse;
import org.project.models.entities.Customer;
import org.project.models.entities.ProductReview;
import org.project.models.dtos.ReviewDTO;
import org.project.services.CustomerService;
import org.project.services.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product_reviews")
public class ProductReviewController {
    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<APIResponse<ProductReview>> add(@RequestBody ProductReview review, @RequestParam Long customerId) {
        if (review == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Invalid review provided."));
        }

        if (customerId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Invalid customerID provided."));
        }

        Customer reviewer = customerService.findById(customerId);
        if (reviewer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null, "Customer not found."));
        }

        review.setReviewer(reviewer);
        ProductReview createdProductReview = productReviewService.add(review);
        if (createdProductReview == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null, "Failed to add ProductReview."));
        }

        return ResponseEntity.ok(
                new APIResponse<>(review, "ProductReview added successfully."));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ProductReview>> update(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Invalid ID provided."));
        }

        if (reviewDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Invalid reviewDTO provided."));
        }

        ProductReview review = productReviewService.findById(id);
        if (review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null, "ProductReview not found."));
        }

        ProductReview updatedReview = productReviewService.update(id, reviewDTO);
        if (updatedReview == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null, "Failed to update ProductReview."));
        }

        return ResponseEntity.ok(
                new APIResponse<>(review, "ProductReview updated successfully."));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<APIResponse<ProductReview>> deleteById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Invalid ID provided."));
        }

        ProductReview review = productReviewService.findById(id);
        if (review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null, "ProductReview not found."));
        }

        productReviewService.deleteById(id);
        return ResponseEntity.ok(
                new APIResponse<>(review, "ProductReview deleted successfully."));
    }

    @DeleteMapping
    public ResponseEntity<APIResponse<Iterable<ProductReview>>> deleteAll() {
        Iterable<ProductReview> reviews = productReviewService.findAll();
        if (!reviews.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null, "No ProductReviews found."));
        }

        productReviewService.deleteAll();
        return ResponseEntity.ok(
                new APIResponse<>(reviews, "All ProductReviews deleted successfully."));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponse<ProductReview>> findById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new APIResponse<>(null, "Invalid ID provided."));
        }

        ProductReview productReview = productReviewService.findById(id);
        if (productReview == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(null, "ProductReview not found."));
        }

        return ResponseEntity.ok(
                new APIResponse<>(productReview, "ProductReview retrieved successfully."));
    }

    @GetMapping("/reviewer/{reviewerId}")
    public ResponseEntity<APIResponse<Iterable<ProductReview>>> findAllByReviewerId(@PathVariable Long reviewerId) {
        if (reviewerId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new APIResponse<>(null, "Invalid ID provided."));
        }

        Iterable<ProductReview> productReviews = productReviewService.findAllByReviewerId(reviewerId);
        if (!productReviews.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(null, "No ProductReviews found for the given reviewerID."));
        }

        return ResponseEntity.ok(
                new APIResponse<>(productReviews, "Product reviews retrieved successfully."));
    }

    @GetMapping
    public ResponseEntity<APIResponse<Iterable<ProductReview>>> findAll() {
        Iterable<ProductReview> productReviews = productReviewService.findAll();
        if (!productReviews.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(null, "No ProductReviews found."));
        }

        return ResponseEntity.ok(
                new APIResponse<>(productReviews, "Product reviews retrieved successfully."));
    }
}
