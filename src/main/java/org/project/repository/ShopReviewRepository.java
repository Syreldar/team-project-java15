package org.project.repository;

import org.project.models.Product;
import org.project.models.ProductReview;
import org.project.models.Shop;
import org.project.models.ShopReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopReviewRepository extends JpaRepository<ShopReview, Long> {
    List<ShopReview> findByReviewedShop(Shop shop);

    List<ShopReview> getRecentReview(Shop shop, int limit);

    Optional<ShopReview> findById(Long id);
    List<ShopReview> findByReviewedShop_Id (Long id);
}

