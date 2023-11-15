package org.project.controllers;

import org.project.models.APIResponse;
import org.project.models.entities.Customer;
import org.project.models.entities.ShopReview;
import org.project.models.dtos.ReviewDTO;
import org.project.services.CustomerService;
import org.project.services.ShopReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop_reviews")
public class ShopReviewController {
    @Autowired
    private ShopReviewService shopReviewService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<APIResponse<ShopReview>> add(@RequestBody ShopReview review, @RequestParam Long customerId) {
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
        ShopReview createdShopReview = shopReviewService.add(review);
        if (createdShopReview == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null, "Failed to add ShopReview."));
        }

        return ResponseEntity.ok(
                new APIResponse<>(review, "ShopReview added successfully."));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ShopReview>> update(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Invalid ID provided."));
        }

        if (reviewDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Invalid reviewDTO provided."));
        }

        ShopReview review = shopReviewService.findById(id);
        if (review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null, "ShopReview not found."));
        }

        ShopReview updatedReview = shopReviewService.update(id, reviewDTO);
        if (updatedReview == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null, "Failed to update ShopReview."));
        }
        return ResponseEntity.ok(
                new APIResponse<>(review, "ShopReview updated successfully."));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<APIResponse<ShopReview>> deleteById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Invalid ID provided."));
        }

        ShopReview review = shopReviewService.findById(id);
        if (review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null, "ShopReview not found."));
        }

        shopReviewService.deleteById(id);
        return ResponseEntity.ok(
                new APIResponse<>(review, "ShopReview deleted successfully."));
    }

    @DeleteMapping
    public ResponseEntity<APIResponse<Iterable<ShopReview>>> deleteAll() {
        Iterable<ShopReview> reviews = shopReviewService.findAll();
        if (!reviews.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null, "No ShopReviews found."));
        }

        shopReviewService.deleteAll();
        return ResponseEntity.ok(
                new APIResponse<>(reviews, "All ShopReviews deleted successfully."));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponse<ShopReview>> findById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new APIResponse<>(null, "Invalid ID provided."));
        }

        ShopReview shopReview = shopReviewService.findById(id);
        if (shopReview == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(null, "ShopReview not found."));
        }

        return ResponseEntity.ok(
                new APIResponse<>(shopReview, "ShopReview retrieved successfully."));
    }

    @GetMapping("/reviewer/{reviewerId}")
    public ResponseEntity<APIResponse<Iterable<ShopReview>>> findAllByReviewerId(@PathVariable Long reviewerId) {
        if (reviewerId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new APIResponse<>(null, "Invalid ID provided."));
        }

        Iterable<ShopReview> shopReviews = shopReviewService.findAllByReviewerId(reviewerId);
        if (!shopReviews.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(null, "No ShopReviews found for the given reviewerID."));
        }

        return ResponseEntity.ok(
                new APIResponse<>(shopReviews, "Shop reviews retrieved successfully."));
    }

    @GetMapping
    public ResponseEntity<APIResponse<Iterable<ShopReview>>> findAll() {
        Iterable<ShopReview> shopReviews = shopReviewService.findAll();
        if (!shopReviews.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(null, "No ShopReviews found."));
        }

        return ResponseEntity.ok(
                new APIResponse<>(shopReviews, "Shop reviews retrieved successfully."));
    }
}
