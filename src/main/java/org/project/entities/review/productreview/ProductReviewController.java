package org.project.entities.review.productreview;

import jakarta.validation.Valid;
import org.project.entities.product.ProductService;
import org.project.helpers.APIResponse;
import org.project.entities.customer.CustomerService;
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

    @Autowired
    private ProductService productService;


    @PostMapping("/{customerId}/{productId}")
    public ResponseEntity<APIResponse<ProductReview>> add(
            @Valid @RequestBody ProductReviewDTO reviewDTO,
            @PathVariable Long customerId,
            @PathVariable Long productId)
    {
        try {
            ProductReview createdProductReview = productReviewService.add(customerId, productId, reviewDTO);
            return ResponseEntity.ok(
                    new APIResponse<>(createdProductReview, "ProductReview added successfully."));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to add ProductReview."));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ProductReview>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductReviewDTO reviewDTO)
    {
        try {
            ProductReview updatedProductReview = productReviewService.update(id, reviewDTO);
            return ResponseEntity.ok(
                    new APIResponse<>(updatedProductReview,
                            String.format("ProductReview with ID %d updated successfully.", id)));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            String.format("Failed to update ProductReview with ID %d.", id)));
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<APIResponse<ProductReview>> deleteById(@PathVariable Long id) {
        try {
            productReviewService.deleteById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(null,
                            String.format("ProductReview with ID %d deleted successfully.", id)));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            String.format("Failed to delete ProductReview with ID %d.", id)));
        }
    }

    @DeleteMapping
    public ResponseEntity<APIResponse<Iterable<ProductReview>>> deleteAll() {
        try {
            productReviewService.deleteAll();
            return ResponseEntity.ok(
                    new APIResponse<>(null, "All ProductReviews deleted successfully."));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            "Failed to delete all ProductReviews"));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponse<ProductReview>> findById(@PathVariable Long id) {
        try {
            ProductReview productReview = productReviewService.findById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(productReview,
                            String.format("ProductReview with ID %d retrieved successfully.", id)));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            String.format("Failed to find ProductReview with ID %d.", id)));
        }
    }

    @GetMapping("/reviewer/{reviewerId}")
    public ResponseEntity<APIResponse<Iterable<ProductReview>>> findAllByReviewerId(@PathVariable Long reviewerId) {
        try {
            Iterable<ProductReview> productReviews = productReviewService.findAllByReviewerId(reviewerId);
            return ResponseEntity.ok(
                    new APIResponse<>(productReviews,
                            String.format("ProductReviews with reviewer ID %d retrieved successfully.", reviewerId)));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            String.format("Failed to find ProductReviews with reviewer ID %d.", reviewerId)));
        }
    }

    @GetMapping
    public ResponseEntity<APIResponse<Iterable<ProductReview>>> findAll() {
        try {
            Iterable<ProductReview> productReviews = productReviewService.findAll();
            return ResponseEntity.ok(
                    new APIResponse<>(productReviews, "All ProductReviews retrieved successfully."));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            "Failed to find all ProductReviews."));
        }
    }
}
