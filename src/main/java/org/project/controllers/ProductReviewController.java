package org.project.controllers;

import org.project.models.ProductReview;
import org.project.models.dtos.ReviewDTO;
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


    @PostMapping
    public ResponseEntity<ProductReview> add(@RequestBody ProductReview review) {
        ProductReview createdProductReview = productReviewService.add(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductReview> update(@PathVariable Long id, @RequestBody ReviewDTO review) {
        ProductReview updatedProductReview = productReviewService.update(id, review);
        return ResponseEntity.ok(updatedProductReview);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productReviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        productReviewService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductReview> findById(@PathVariable Long id) {
        ProductReview productReview = productReviewService.findById(id);
        return ResponseEntity.ok(productReview);
    }

    @GetMapping("/reviewer/{reviewerId}")
    public ResponseEntity<Iterable<ProductReview>> findAllByReviewerId(@PathVariable Long reviewerId) {
        Iterable<ProductReview> productReview = productReviewService.findAllByReviewerId(reviewerId);
        return ResponseEntity.ok(productReview);
    }

}
