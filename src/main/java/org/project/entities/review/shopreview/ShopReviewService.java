package org.project.entities.review.shopreview;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.project.entities.customer.CustomerRepository;
import org.project.entities.review.ReviewDTO;
import org.project.entities.customer.Customer;
import org.project.entities.shop.Shop;
import org.project.entities.shop.ShopRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ShopReviewService {
    @Autowired
    private ShopReviewRepository shopReviewRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public ShopReview add(Long customerId, Long shopId, ShopReviewDTO reviewDTO) {
        if (reviewDTO == null) {
            throw new IllegalArgumentException("Review DTO cannot be null");
        }
        if (customerId == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        if (shopId == null) {
            throw new IllegalArgumentException("Shop ID cannot be null");
        }

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Customer with ID %d not found", customerId)));
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Shop with ID %d not found", shopId)));

        reviewDTO.setReviewedShop(shop);

        ShopReview shopReview = convertToEntity(shop, customer, reviewDTO);
        shopReview.setReviewer(customer);
        shop.addReview(shopReview);
        //customer.addReview(shopReview);

        try {
            return shopReviewRepository.save(shopReview);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error saving ShopReview", e);
        }
    }

    private ShopReview convertToEntity(
            Shop reviewedShop,
            Customer reviewer,
            ReviewDTO shopReviewDTO)
    {
        return new ShopReview(
                reviewedShop,
                reviewer,
                shopReviewDTO.getRating(),
                shopReviewDTO.getComment(),
                shopReviewDTO.getCreationDate(),
                shopReviewDTO.getUpdateDate()
        );
    }

    @Transactional
    public ShopReview update(Long id, ShopReviewDTO reviewDTO) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (reviewDTO == null) {
            throw new IllegalArgumentException("ReviewDTO cannot be null");
        }

        ShopReview review = this.findById(id);

        double rating = reviewDTO.getRating();
        if (rating != 0) {
            review.setRating(rating);
        }

        String comment = reviewDTO.getComment();
        if (comment != null && !comment.isEmpty()) {
            review.setComment(comment);
        }

        review.setUpdateDate(LocalDateTime.now());

        try {
            return shopReviewRepository.save(review);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error updating ShopReview", e);
        }
    }

    @Transactional
    public void delete(ShopReview review) {
        if (review == null) {
            throw new IllegalArgumentException("Review cannot be null");
        }

        try {
            shopReviewRepository.delete(review);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error deleting ShopReview", e);
        }
    }

    @Transactional(readOnly = true)
    public ShopReview findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        try {
            return shopReviewRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            String.format("ShopReview with ID %d not found", id)));
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error finding ShopReview with ID %d", id), e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<ShopReview> findAllByReviewedShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }

        if (!shopRepository.existsById(shop.getId())) {
            throw new EntityNotFoundException(
                    String.format("Shop with ID %d not found", shop.getId()));
        }

        try {
            return shopReviewRepository.findAllByReviewedShop(shop);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error finding ShopReviews for Shop with ID %d", shop.getId()), e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<ShopReview> findAllByReviewedShop_Id(Long shopId) {
        if (shopId == null) {
            throw new IllegalArgumentException("Shop ID cannot be null");
        }

        if (!shopRepository.existsById(shopId)) {
            throw new EntityNotFoundException(
                    String.format("Shop with ID %d not found", shopId));
        }

        try {
            return shopReviewRepository.findAllByReviewedShop_Id(shopId);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error finding ShopReviews for Shop with ID %d", shopId), e);
        }
    }

    @Transactional(readOnly = true)
    // Should be Iterable cause a Customer can leave multiple Shop Reviews. TODO: Should he be able to..?
    public Iterable<ShopReview> findAllByReviewer(Customer reviewer) {
        if (reviewer == null) {
            throw new IllegalArgumentException("Reviewer cannot be null");
        }

        if (!customerRepository.existsById(reviewer.getId())) {
            throw new EntityNotFoundException(
                    String.format("Customer with ID %d not found", reviewer.getId()));
        }

        try {
            return shopReviewRepository.findAllByReviewer(reviewer);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error finding ShopReviews for Customer with ID %d", reviewer.getId()), e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<ShopReview> findAllByReviewerId(Long reviewerId) {
        if (reviewerId == null) {
            throw new IllegalArgumentException("Reviewer ID cannot be null");
        }

        if (!customerRepository.existsById(reviewerId)) {
            throw new EntityNotFoundException(
                    String.format("Customer with ID %d not found", reviewerId));
        }

        try {
            return shopReviewRepository.findAllByReviewerId(reviewerId);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error finding ShopReviews for Customer with ID %d", reviewerId), e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<ShopReview> findAll() {
        try {
            return shopReviewRepository.findAll();
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error finding all ShopReviews", e);
        }
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        if (!shopReviewRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    String.format("ShopReview with ID %d not found", id));
        }

        try {
            shopReviewRepository.deleteById(id);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error deleting ShopReview with ID %d", id), e);
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
            shopReviewRepository.deleteAllByShop(shop);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error deleting ShopReviews for Shop with ID %d", shop.getId()), e);
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
            shopReviewRepository.deleteAllByShopId(shopId);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error deleting ShopReviews for Shop with ID %d", shopId), e);
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
            shopReviewRepository.deleteAllByReviewer(reviewer);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error deleting ShopReviews for Customer with ID %d", reviewer.getId()), e);
        }
    }

    @Transactional
    public void deleteAll(Iterable<ShopReview> reviews) {
        if (reviews == null) {
            throw new IllegalArgumentException("Reviews cannot be null");
        }

        for (ShopReview review : reviews) {
            if (!shopReviewRepository.existsById(review.getId())) {
                throw new EntityNotFoundException(
                        String.format("ShopReview with ID %d not found", review.getId()));
            }
        }

        try {
            shopReviewRepository.deleteAll(reviews);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error deleting ShopReviews", e);
        }
    }

    @Transactional
    public void deleteAll() {
        try {
            shopReviewRepository.deleteAll();
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error deleting all ShopReviews", e);
        }
    }
}
