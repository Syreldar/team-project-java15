package org.project.repository;


import org.project.models.Customer;
import org.project.models.Product;
import org.project.models.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    List<ProductReview> findByReviewedProduct(Long product);
    List<ProductReview> findByRating(float product);
    List<ProductReview> getRecentReviews (Product product, int limit);
    List<ProductReview> findByReviewer (Customer reviewer);
    Optional<ProductReview> findById(Long id);
    List<ProductReview> findByReviewedProduct_Id(Long productId);


}
