package org.project.services;

import jakarta.persistence.EntityNotFoundException;
import org.project.models.dtos.ReviewDTO;
import org.project.models.entities.Customer;
import org.project.models.entities.Shop;
import org.project.models.entities.ShopReview;
import org.springframework.transaction.annotation.Transactional;
import org.project.repositories.ShopReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShopReviewService {
    @Autowired
    private ShopReviewRepository shopReviewRepository;

    @Transactional
    public ShopReview add(ShopReview review) {
        return shopReviewRepository.save(review);
    }

    @Transactional
    public ShopReview update(Long id, ReviewDTO reviewDTO) {
        ShopReview review = shopReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ShopReview not found"));

        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setUpdateDate(LocalDate.now());
        return shopReviewRepository.save(review);
    }

    @Transactional
    public void delete(ShopReview review) {
        shopReviewRepository.delete(review);
    }

    @Transactional(readOnly = true)
    public ShopReview findById(Long id) {
        return shopReviewRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Iterable<ShopReview> findAllByReviewedShop(Shop shop) {
        return shopReviewRepository.findAllByReviewedShop(shop);
    }

    @Transactional(readOnly = true)
    public Iterable<ShopReview> findAllByReviewedShop_Id(Long shopId) {
        return shopReviewRepository.findAllByReviewedShop_Id(shopId);
    }

    @Transactional(readOnly = true)
    // Should be Iterable cause a Customer can leave multiple Shop Reviews. TODO: Should he be able to..?
    public Iterable<ShopReview> findAllByReviewer(Customer reviewer) {
        return shopReviewRepository.findAllByReviewer(reviewer);
    }

    @Transactional(readOnly = true)
    public Iterable<ShopReview> findAllByReviewerId(Long reviewerId) {
        return shopReviewRepository.findAllByReviewerId(reviewerId);
    }

    @Transactional(readOnly = true)
    public Iterable<ShopReview> findAll() {
        return shopReviewRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        shopReviewRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllByShop(Shop shop) {
        shopReviewRepository.deleteAllByShop(shop);
    }

    @Transactional
    public void deleteAllByShopId(Long shopId) {
        shopReviewRepository.deleteAllByShopId(shopId);
    }

    @Transactional
    public void deleteAllByReviewer(Customer reviewer) {
        shopReviewRepository.deleteAllByReviewer(reviewer);
    }

    @Transactional
    public void deleteAll(Iterable<ShopReview> reviews) {
        shopReviewRepository.deleteAll(reviews);
    }

    @Transactional
    public void deleteAll() {
        shopReviewRepository.deleteAll();
    }
}
