package org.project.entities.review.shopreview;

import jakarta.validation.Valid;
import org.project.helpers.APIResponse;
import org.project.entities.customer.CustomerService;
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


    @PostMapping("/{customerId}/{shopId}")
    public ResponseEntity<APIResponse<ShopReview>> add(
            @Valid @RequestBody ShopReviewDTO reviewDTO,
            @PathVariable Long customerId,
            @PathVariable Long shopId)
    {
        try {
            ShopReview createdShopReview = shopReviewService.add(customerId, shopId, reviewDTO);
            return ResponseEntity.ok(
                    new APIResponse<>(createdShopReview, "ShopReview added successfully."));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to add ShopReview."));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ShopReview>> update(
            @PathVariable Long id,
            @Valid @RequestBody ShopReviewDTO reviewDTO)
    {
        try {
            ShopReview updatedShopReview = shopReviewService.update(id, reviewDTO);
            return ResponseEntity.ok(
                    new APIResponse<>(updatedShopReview,
                            String.format("ShopReview with ID %d updated successfully.", id)));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            String.format("Failed to update ShopReview with ID %d.", id)));
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<APIResponse<ShopReview>> deleteById(@PathVariable Long id) {
        try {
            shopReviewService.deleteById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(null,
                            String.format("ShopReview with ID %d deleted successfully.", id)));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            String.format("Failed to delete ShopReview with ID %d.", id)));
        }
    }

    @DeleteMapping
    public ResponseEntity<APIResponse<Iterable<ShopReview>>> deleteAll() {
        try {
            shopReviewService.deleteAll();
            return ResponseEntity.ok(
                    new APIResponse<>(null, "All ShopReviews deleted successfully."));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            "Failed to delete all ShopReviews"));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponse<ShopReview>> findById(@PathVariable Long id) {
        try {
            ShopReview shopReview = shopReviewService.findById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(shopReview,
                            String.format("ShopReview with ID %d retrieved successfully.", id)));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            String.format("Failed to find ShopReview with ID %d.", id)));
        }
    }

    @GetMapping("/reviewer/{reviewerId}")
    public ResponseEntity<APIResponse<Iterable<ShopReview>>> findAllByReviewerId(@PathVariable Long reviewerId) {
        try {
            Iterable<ShopReview> shopReviews = shopReviewService.findAllByReviewerId(reviewerId);
            return ResponseEntity.ok(
                    new APIResponse<>(shopReviews,
                            String.format("ShopReviews with reviewer ID %d retrieved successfully.", reviewerId)));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            String.format("Failed to find ShopReviews with reviewer ID %d.", reviewerId)));
        }
    }

    @GetMapping
    public ResponseEntity<APIResponse<Iterable<ShopReview>>> findAll() {
        try {
            Iterable<ShopReview> shopReviews = shopReviewService.findAll();
            return ResponseEntity.ok(
                    new APIResponse<>(shopReviews, "All ShopReviews retrieved successfully."));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            "Failed to find all ShopReviews."));
        }
    }
}
