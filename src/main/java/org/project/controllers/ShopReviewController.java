package org.project.controllers;

import org.project.models.ShopReview;
import org.project.models.dtos.ReviewDTO;
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


    @PostMapping
    public ResponseEntity<ShopReview> add(@RequestBody ShopReview review) {
        ShopReview createdShopReview = shopReviewService.add(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShopReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShopReview> update(@PathVariable Long id, @RequestBody ReviewDTO review) {
        ShopReview updatedShopReview = shopReviewService.update(id, review);
        return ResponseEntity.ok(updatedShopReview);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        shopReviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        shopReviewService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ShopReview> findById(@PathVariable Long id) {
        ShopReview productReview = shopReviewService.findById(id);
        return ResponseEntity.ok(productReview);
    }

    @GetMapping("/reviewer/{reviewerId}")
    public ResponseEntity<Iterable<ShopReview>> findAllByReviewerId(@PathVariable Long reviewerId) {
        Iterable<ShopReview> productReview = shopReviewService.findAllByReviewerId(reviewerId);
        return ResponseEntity.ok(productReview);
    }

}