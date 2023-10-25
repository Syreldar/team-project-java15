package org.project.services;

import org.project.models.Customer;
import org.project.models.Product;
import org.project.models.ProductReview;
import org.project.repository.ProductRepository;
import org.project.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductReviewService {
    @Autowired
    private final ProductReviewRepository productReviewRepository;
    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    public ProductReviewService(ProductReviewRepository productReviewRepository, ProductRepository productRepository) {
        this.productReviewRepository = productReviewRepository;
        this.productRepository = productRepository;
    }

    public void ReviewbyId(Long id) {
        Optional<ProductReview> productReviewOptional = productReviewRepository.findById(id);
    }

    public ProductReview createProductReview(Product reviewedProduct, Customer reviewer, float rating, String comment) {
        ProductReview productReview = new ProductReview(reviewedProduct, reviewer, rating, comment);
        // Implement logic to save the product review to the repository.
        return productReviewRepository.save(productReview);
    }

    public void updateProductReview(ProductReview productReview) {
        // Implement logic to update the product review details using the repository.
        productReviewRepository.save(productReview);
    }

    public ProductReview getProductReviewById(Long reviewId) {
        return productReviewRepository.findById(reviewId).orElse(null);
    }

    public List<ProductReview> getProductReviewsByProduct(Product product) {
        return productReviewRepository.findByReviewedProduct(product.getId());
    }

    public List<ProductReview> getProductReviewsByReviewer(Customer reviewer) {
        return productReviewRepository.findByReviewer(reviewer);
    }

    public double calculateAverageRating(Product product) {
        List<ProductReview> reviews = productReviewRepository.findByReviewedProduct(product.getId());
        if (reviews.isEmpty()) {
            return 0.0; // O una scelta appropriata in caso di nessuna recensione.
        }

        double totalRating = reviews.stream().mapToDouble(ProductReview::getRating).sum();
        return totalRating / reviews.size();
    }

    public void deleteProductReview(Long reviewId) {
        productReviewRepository.deleteById(reviewId);
    }

    public List<ProductReview> getRecentProductReviews(Product product, int limit) {
        return productReviewRepository.getRecentReviews(product, limit);
    }

    public List<ProductReview> getProductReviewsWithRatingGreaterThan(float minRating) {
        return productReviewRepository.findByRating(minRating);
    }

    public void createProductReview(Long id, float rating, String comment, Customer reviewer) {
        // Trova il prodotto con l'ID specificato

        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            // Crea una nuova recensione del prodotto
            ProductReview productReview = new ProductReview(product, reviewer, rating, comment);

            // Salva la recensione nel repository delle recensioni dei prodotti
            productReviewRepository.save(productReview);
        } else {
            // Gestisci il caso in cui il prodotto non sia stato trovato
            // Puoi lanciare un'eccezione o eseguire un'altra azione personalizzata.
        }
    }

    public List<ProductReview> getProductReviewsByProductId(Long id) {
        return productReviewRepository.findByReviewedProduct(id);
    }



}