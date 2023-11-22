package org.project.entities.shop;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.project.entities.product.Category;
import org.project.entities.product.Product;
import org.project.entities.product.ProductDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    @Transactional
    public Shop add(ShopDTO shopDTO) {
        if (shopDTO == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }
        try {
            Shop shop = convertToEntity(shopDTO);
            return shopRepository.save(shop);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error saving Shop", e);
        }
    }

    private Shop convertToEntity(ShopDTO shopDTO) {
        return new Shop(
                shopDTO.getName(),
                shopDTO.getOwnerName()
        );
    }

    @Transactional
    public Shop update(Long id, ShopDTO shopDTO) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (shopDTO == null) {
            throw new IllegalArgumentException("ShopDTO cannot be null");
        }

        Shop shop = this.findById(id);

        String shopName = shopDTO.getName();
        if (shopName != null && !shopName.isEmpty()) {
            shop.setName(shopName);
        }

        String ownerName = shopDTO.getOwnerName();
        if (ownerName != null && !ownerName.isEmpty()) {
            shop.setOwnerName(ownerName);
        }

        List<Product> products = shopDTO.getProducts();
        if (products != null && !products.isEmpty()) {
            shop.setProducts(products);
        }

        try {
            return shopRepository.save(shop);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error updating Shop", e);
        }
    }

    @Transactional
    public void delete(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }

        try {
            shopRepository.delete(shop);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error deleting Shop", e);
        }
    }

    @Transactional(readOnly = true)
    public Shop findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        try {
            return shopRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            String.format("Shop with ID %d not found", id)));
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error finding Shop", e);
        }
    }

    @Transactional(readOnly = true)
    // Should be Iterable cause there can be Multiple shops with the same name.
    public Iterable<Shop> findAllByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (!shopRepository.existsByName(name)) {
            throw new EntityNotFoundException(
                    String.format("Shop with name %s not found", name));
        }

        try {
            return shopRepository.findAllByName(name);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error finding Shop", e);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<Shop> findAll() {
        try {
            return shopRepository.findAll();
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error finding all Shops", e);
        }
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        if (!shopRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    String.format("Shop with ID %d not found", id));
        }

        try {
            shopRepository.deleteById(id);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error deleting Shop", e);
        }
    }

    @Transactional
    public void deleteAllByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (!shopRepository.existsByName(name)) {
            throw new EntityNotFoundException(
                    String.format("Shop with name %s not found", name));
        }

        try {
            shopRepository.deleteAllByName(name);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error deleting all shops", e);
        }
    }

    @Transactional
    public void deleteAll() {
        try {
            shopRepository.deleteAll();
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error deleting all shops", e);
        }
    }
}
