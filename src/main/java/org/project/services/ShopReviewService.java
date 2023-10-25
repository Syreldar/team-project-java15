package org.project.services;

import org.project.models.Customer;
import org.project.models.Product;
import org.project.models.Shop;
import org.project.models.ShopReview;
import org.project.repository.ShopRepository;
import org.project.repository.ShopReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopReviewService {
    private final ShopReviewRepository shopReviewRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    public ShopReviewService(ShopReviewRepository shopReviewRepository) {
        this.shopReviewRepository = shopReviewRepository;
    }
    public void ReviewbyId(Long id){
        Optional<ShopReview> ShopReviewOptional= shopReviewRepository.findById(id);
    }

    public void createShopReview(Shop reviewedShop, Customer reviewer, float rating, String comment) {
        ShopReview shopReview = new ShopReview(reviewedShop, reviewer, rating, comment);
        shopReviewRepository.save(shopReview);
    }

    public void updateShopReview(ShopReview shopReview) {
        shopReviewRepository.save(shopReview);
    }

    public ShopReview getShopReviewById(Long reviewId) {
        return shopReviewRepository.findById(reviewId).orElse(null);
    }

    public List<ShopReview> getShopReviewsByShop(Shop shop) {
        return shopReviewRepository.findByReviewedShop(shop);
    }

    public List<ShopReview> getRecentShopReviews(Shop shop, int limit) {
        return shopReviewRepository.getRecentReview(shop, limit);
    }

    public double calculateAverageRating(Shop shop) {
        List<ShopReview> reviews = shopReviewRepository.findByReviewedShop(shop);
        if (reviews.isEmpty()) {
            return 0.0; // O un valore appropriato in caso di nessuna recensione.
        }

        double totalRating = reviews.stream().mapToDouble(ShopReview::getRating).sum();
        return totalRating / reviews.size();
    }
    public double calculateOverallAverageRating() {
        List<ShopReview> allReviews = shopReviewRepository.findAll();
        if (allReviews.isEmpty()) {
            return 0.0; // O un valore appropriato in caso di nessuna recensione.
        }

        double totalRating = allReviews.stream().mapToDouble(ShopReview::getRating).sum();
        return totalRating / allReviews.size();
    }

        public void deleteShopReview(Long reviewId) {
        shopReviewRepository.deleteById(reviewId);
    }

    public void createShopReview(Long id, float rating, String comment, Customer customer   ) {
        // Assicurati di trovare il negozio con l'ID specificato.
        Shop shop = shopRepository.findById(id).orElse(null);

        if (shop != null) {
            // Crea una nuova recensione del negozio.
            ShopReview shopReview = new ShopReview(shop,customer, rating, comment);

            // Salva la recensione nel repository delle recensioni dei negozi.
            shopReviewRepository.save(shopReview);
        } else {
            // Gestisci il caso in cui il negozio non sia stato trovato.
            // Puoi lanciare un'eccezione o eseguire un'altra azione personalizzata.
        }
    }

    public List<ShopReview> getShopReviewsByShopId(Long id) {
        return shopReviewRepository.findByReviewedShop_Id(id);
    }
    }

// Altri metodi specifici delle recensioni dei negozi, se necessario.
