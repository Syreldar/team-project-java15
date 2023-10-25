package org.project.services;
import org.project.models.Customer;
import org.project.models.Product;
import org.project.models.Shop;
import org.project.models.ShopReview;
import org.project.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }
    public Shop createShop(String name, String ownerName) {
        // Crea una nuova istanza di Shop
        Shop shop = new Shop(name, ownerName);

        // Salva il negozio nel repository
        shop = shopRepository.save(shop);

        return shop;
    }

    public void updateShop(Long shopId, String name, String ownerName) {
        // Retrieve the existing shop from the repository using the ID.
        Shop existingShop = shopRepository.findById(shopId).orElse(null);

        // Check if the shop exists.
        if (existingShop != null) {
            // Update the shop details with the new values.
            existingShop.setName(name);
            existingShop.setOwnerName(ownerName);

            // Save the updated shop in the repository.
            shopRepository.save(existingShop);
        }
    }
    public boolean sellProduct(Shop shop, Customer customer, Product product, int quantity) {
        if (shop == null || customer == null || product == null) {
            throw new IllegalArgumentException("Invalid input.");
        }

        boolean sold = shop.sellProduct(customer, product, quantity);
        if (sold) {
            // Implement logic to update the shop's sales statistics using the repository.
            shopRepository.save(shop);
        }

        return sold;
    }

    public void addReview(Shop shop, ShopReview review) {
        if (shop != null && review != null) {
            shop.addReview(review);
            // Implement logic to update the shop's reviews using the repository.
            shopRepository.save(shop);
        }
    }

    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    public Shop getShopById(Long shopId) {
        return shopRepository.findById(shopId).orElse(null);
    }
    public void deleteShop(Long shopId) {
        shopRepository.deleteById(shopId);
    }
    public List<Shop> getShopsByOwner(String ownerName) {
        return shopRepository.findByOwnerName(ownerName);
    }


}
