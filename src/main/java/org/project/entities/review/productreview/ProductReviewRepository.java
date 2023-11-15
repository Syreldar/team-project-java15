package org.project.entities.review.productreview;

import org.project.entities.customer.Customer;
import org.project.entities.product.Product;
import org.project.entities.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    @Query("SELECT pr FROM ProductReview pr WHERE :shop MEMBER OF pr.reviewedProduct.shops")
    Iterable<ProductReview> findAllByShop(@Param("shop") Shop shop);

    @Query("SELECT pr FROM ProductReview pr JOIN pr.reviewedProduct.shops shop WHERE shop.id = :shopId")
    Iterable<ProductReview> findAllByShopId(@Param("shopId") Long shopId);

    Iterable<ProductReview> findAllByReviewer(Customer reviewer);
    Iterable<ProductReview> findAllByReviewerId(Long id);
    Iterable<ProductReview> findAllByReviewedProduct(Product product);
    Iterable<ProductReview> findAllByReviewedProduct_Id(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM ProductReview pr WHERE pr.reviewedProduct IN (SELECT p FROM Product p JOIN p.shops shop WHERE shop = :shop)")
    void deleteAllByShop(@Param("shop") Shop shop);

    @Modifying
    @Transactional
    @Query("DELETE FROM ProductReview pr WHERE pr.reviewedProduct IN (SELECT p FROM Product p JOIN p.shops shop WHERE shop.id = :shopId)")
    void deleteAllByShopId(@Param("shopId") Long shopId);

    @Transactional
    void deleteAllByReviewer(Customer reviewer);
}
