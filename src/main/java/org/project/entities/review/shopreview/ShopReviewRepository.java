package org.project.entities.review.shopreview;

import org.project.entities.customer.Customer;
import org.project.entities.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ShopReviewRepository extends JpaRepository<ShopReview, Long> {
    @Transactional(readOnly = true)
    Iterable<ShopReview> findAllByReviewer(Customer reviewer);

    @Transactional(readOnly = true)
    Iterable<ShopReview> findAllByReviewerId(Long id);

    @Transactional(readOnly = true)
    Iterable<ShopReview> findAllByReviewedShop(Shop shop);

    @Transactional(readOnly = true)
    Iterable<ShopReview> findAllByReviewedShop_Id(Long shopId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ShopReview sr WHERE sr.reviewedShop = :shop")
    void deleteAllByShop(@Param("shop") Shop shop);

    @Modifying
    @Transactional
    @Query("DELETE FROM ShopReview sr WHERE sr.reviewedShop.id = :shopId")
    void deleteAllByShopId(@Param("shopId") Long shopId);

    @Transactional
    void deleteAllByReviewer(Customer reviewer);
}
