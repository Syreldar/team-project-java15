package org.project.services;

import jakarta.persistence.EntityNotFoundException;
import org.project.models.entities.Product;
import org.project.models.dtos.ShopDTO;
import org.springframework.transaction.annotation.Transactional;
import org.project.models.entities.Shop;
import org.project.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    @Transactional
    public Shop add(Shop shop) {
        return shopRepository.save(shop);
    }

    @Transactional
    public Shop update(Long id, ShopDTO shopDTO) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        String shopName = shop.getName();
        if (shopName != null) {
            shop.setName(shopName);
        }

        String ownerName = shopDTO.getOwnerName();
        if (ownerName != null) {
            shop.setOwnerName(ownerName);
        }

        List<Product> products = shopDTO.getProducts();
        if (products != null && !products.isEmpty()) {
            shop.setProducts(products);
        }

        return shopRepository.save(shop);
    }

    @Transactional
    public void delete(Shop shop) {
        shopRepository.delete(shop);
    }

    @Transactional(readOnly = true)
    public Shop findById(Long id) {
        return shopRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    // Should be Iterable cause there can be Multiple shops with the same name.
    public Iterable<Shop> findAllByName(String name) {
        return shopRepository.findAllByName(name);
    }

    @Transactional(readOnly = true)
    public Iterable<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Transactional
    public void deleteByName(String name) {
        shopRepository.deleteByName(name);
    }

    @Transactional
    public void deleteById(Long id) {
        shopRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllByName(String name) {
        shopRepository.deleteAllByName(name);
    }

    @Transactional
    public void deleteAll() {
        shopRepository.deleteAll();
    }
}
