package org.project.repositories;

import org.project.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ShopReviewRepository extends JpaRepository<ShopReview, Long> {
    Iterable<ShopReview> findAllByReviewer(Customer reviewer);
    Iterable<ShopReview> findAllByReviewerId(Long id);
    Iterable<ShopReview> findAllByReviewedShop(Shop shop);
    Iterable<ShopReview> findAllByReviewedShop_Id(Long shopId);
    @Modifying
    @Transactional
    @Query("DELETE FROM ShopReview sr WHERE sr.reviewedShop = :shop")
    void deleteAllByShop(@Param("shop") Shop shop);

    @Modifying
    @Transactional
    @Query("DELETE FROM ShopReview sr WHERE sr.reviewedShop.id = :shopId")
    void deleteAllByShopId(@Param("shopId") Long shopId);

    @Modifying
    @Transactional
    void deleteAllByReviewer(Customer reviewer);
}
