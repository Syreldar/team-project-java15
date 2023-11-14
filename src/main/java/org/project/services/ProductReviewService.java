package org.project.services;

import jakarta.persistence.EntityNotFoundException;
import org.project.models.*;
import org.project.models.dtos.ReviewDTO;
import org.project.repositories.ProductReviewRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProductReviewService {
    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Transactional
    public ProductReview add(ProductReview review) {
        return productReviewRepository.save(review);
    }

    @Transactional
    public ProductReview update(Long id, ReviewDTO reviewDTO) {
        ProductReview review = productReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProductReview not found"));

        double rating = reviewDTO.getRating();
        if (rating != 0) {
            review.setRating(rating);
        }

        String comment = reviewDTO.getComment();
        if (comment != null) {
            review.setComment(comment);
        }

        review.setUpdateDate(LocalDate.now());

        return productReviewRepository.save(review);
    }

    @Transactional
    public void delete(ProductReview review) {
        productReviewRepository.delete(review);
    }

    @Transactional(readOnly = true)
    public ProductReview findById(Long id) {
        return productReviewRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Iterable<ProductReview> findAllByShop(Shop shop) {
        return productReviewRepository.findAllByShop(shop);
    }

    @Transactional(readOnly = true)
    public Iterable<ProductReview> findAllByShopId(Long shopId) {
        return productReviewRepository.findAllByShopId(shopId);
    }

    @Transactional(readOnly = true)
    // Should be Iterable cause a Customer can leave multiple Shop Reviews. TODO: Should he be able to..?
    public Iterable<ProductReview> findAllByReviewer(Customer reviewer) {
        return productReviewRepository.findAllByReviewer(reviewer);
    }

    @Transactional(readOnly = true)
    public Iterable<ProductReview> findAllByReviewerId(Long reviewerId) {
        return productReviewRepository.findAllByReviewerId(reviewerId);
    }

    @Transactional(readOnly = true)
    public Iterable<ProductReview> findAllByReviewedProduct(Product product) {
        return productReviewRepository.findAllByReviewedProduct(product);
    }

    @Transactional(readOnly = true)
    public Iterable<ProductReview> findAllByReviewedProduct_Id(Long productId) {
        return productReviewRepository.findAllByReviewedProduct_Id(productId);
    }

    @Transactional(readOnly = true)
    public Iterable<ProductReview> findAll() {
        return productReviewRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        productReviewRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllByShop(Shop shop) {
        productReviewRepository.deleteAllByShop(shop);
    }

    @Transactional
    public void deleteAllByShopId(Long shopId) {
        productReviewRepository.deleteAllByShopId(shopId);
    }

    @Transactional
    public void deleteAllByReviewer(Customer reviewer) {
        productReviewRepository.deleteAllByReviewer(reviewer);
    }

    @Transactional
    public void deleteAll(Iterable<ProductReview> reviews) {
        productReviewRepository.deleteAll(reviews);
    }

    @Transactional
    public void deleteAll() {
        productReviewRepository.deleteAll();
    }
}
