package org.project.entities.review.productreview;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.project.entities.customer.CustomerRepository;
import org.project.entities.product.Product;
import org.project.entities.product.ProductRepository;
import org.project.entities.review.ReviewDTO;
import org.project.entities.customer.Customer;
import org.project.entities.shop.Shop;
import org.project.entities.shop.ShopRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProductReviewService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Transactional
    public ProductReview add(ProductReview review) {
        if (review == null) {
            throw new IllegalArgumentException("Review cannot be null");
        }

        try {
            return productReviewRepository.save(review);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error saving ProductReview", e);
        }
    }

    @Transactional
    public ProductReview update(Long id, ReviewDTO reviewDTO) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (reviewDTO == null) {
            throw new IllegalArgumentException("ReviewDTO cannot be null");
        }

        ProductReview review = this.findById(id);

        double rating = reviewDTO.getRating();
        if (rating != 0) {
            review.setRating(rating);
        }

        String comment = reviewDTO.getComment();
        if (comment != null && !comment.isEmpty()) {
            review.setComment(comment);
        }

        review.setUpdateDate(LocalDate.now());

        try {
            return productReviewRepository.save(review);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error updating ProductReview", e);
        }
    }

    @Transactional
    public void delete(ProductReview review) {
        if (review == null) {
            throw new IllegalArgumentException("Review cannot be null");
        }

        try {
            productReviewRepository.delete(review);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error deleting ProductReview", e);
        }
    }

    @Transactional(readOnly = true)
    public ProductReview findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        try {
            return productReviewRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            String.format("ProductReview with ID %d not found", id)));
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error finding ProductReview with ID %d", id), e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<ProductReview> findAllByShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }

        if (!shopRepository.existsById(shop.getId())) {
            throw new EntityNotFoundException(
                    String.format("Shop with ID %d not found", shop.getId()));
        }

        try {
            return productReviewRepository.findAllByShop(shop);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error finding ProductReviews for Shop with ID %d", shop.getId()), e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<ProductReview> findAllByShopId(Long shopId) {
        if (shopId == null) {
            throw new IllegalArgumentException("Shop ID cannot be null");
        }

        if (!shopRepository.existsById(shopId)) {
            throw new EntityNotFoundException(
                    String.format("Shop with ID %d not found", shopId));
        }

        try {
            return productReviewRepository.findAllByShopId(shopId);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error finding ProductReviews for Shop with ID %d", shopId), e);
        }
    }

    @Transactional(readOnly = true)
    // Should be Iterable cause a Customer can leave multiple Shop Reviews. TODO: Should he be able to..?
    public Iterable<ProductReview> findAllByReviewer(Customer reviewer) {
        if (reviewer == null) {
            throw new IllegalArgumentException("Reviewer cannot be null");
        }

        if (!customerRepository.existsById(reviewer.getId())) {
            throw new EntityNotFoundException(
                String.format("Customer with ID %d not found", reviewer.getId()));
        }

        try {
            return productReviewRepository.findAllByReviewer(reviewer);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                String.format("Error finding ProductReviews for Customer with ID %d", reviewer.getId()), e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<ProductReview> findAllByReviewerId(Long reviewerId) {
        if (reviewerId == null) {
            throw new IllegalArgumentException("Reviewer ID cannot be null");
        }

        if (!customerRepository.existsById(reviewerId)) {
            throw new EntityNotFoundException(
                String.format("Customer with ID %d not found", reviewerId));
        }

        try {
            return productReviewRepository.findAllByReviewerId(reviewerId);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                String.format("Error finding ProductReviews for Customer with ID %d", reviewerId), e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<ProductReview> findAllByReviewedProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (!productRepository.existsById(product.getId())) {
            throw new EntityNotFoundException(
                String.format("Product with ID %d not found", product.getId()));
        }

        try {
            return productReviewRepository.findAllByReviewedProduct(product);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                String.format("Error finding ProductReviews for Product with ID %d", product.getId()), e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<ProductReview> findAllByReviewedProduct_Id(Long productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        if (!productRepository.existsById(productId)) {
            throw new EntityNotFoundException(
                String.format("Product with ID %d not found", productId));
        }

        try {
            return productReviewRepository.findAllByReviewedProduct_Id(productId);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                String.format("Error finding ProductReviews for Product with ID %d", productId), e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<ProductReview> findAll() {
        return productReviewRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        if (!productReviewRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    String.format("ProductReview with ID %d not found", id));
        }

        try {
            productReviewRepository.deleteById(id);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error deleting ProductReview with ID %d", id), e);
        }
    }

    @Transactional
    public void deleteAllByShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }

        if (!shopRepository.existsById(shop.getId())) {
            throw new EntityNotFoundException(
                    String.format("Shop with ID %d not found", shop.getId()));
        }

        try {
            productReviewRepository.deleteAllByShop(shop);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error deleting ProductReviews for Shop with ID %d", shop.getId()), e);
        }
    }

    @Transactional
    public void deleteAllByShopId(Long shopId) {
        if (shopId == null) {
            throw new IllegalArgumentException("Shop ID cannot be null");
        }

        if (!shopRepository.existsById(shopId)) {
            throw new EntityNotFoundException(
                    String.format("Shop with ID %d not found", shopId));
        }

        try {
            productReviewRepository.deleteAllByShopId(shopId);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error deleting ProductReviews for Shop with ID %d", shopId), e);
        }
    }

    @Transactional
    public void deleteAllByReviewer(Customer reviewer) {
        if (reviewer == null) {
            throw new IllegalArgumentException("Reviewer cannot be null");
        }

        if (!customerRepository.existsById(reviewer.getId())) {
            throw new EntityNotFoundException(
                    String.format("Customer with ID %d not found", reviewer.getId()));
        }

        try {
            productReviewRepository.deleteAllByReviewer(reviewer);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error deleting ProductReviews for Customer with ID %d", reviewer.getId()), e);
        }
    }

    @Transactional
    public void deleteReviews(Iterable<ProductReview> reviews) {
        if (reviews == null) {
            throw new IllegalArgumentException("Reviews cannot be null");
        }

        for (ProductReview review : reviews) {
            if (!productReviewRepository.existsById(review.getId())) {
                throw new EntityNotFoundException(
                        String.format("ProductReview with ID %d not found", review.getId()));
            }
        }

        try {
            productReviewRepository.deleteAll(reviews);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error deleting ProductReviews", e);
        }
    }

    @Transactional
    public void deleteAll() {
        try {
            productReviewRepository.deleteAll();
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error deleting all ProductReviews", e);
        }
    }
}
