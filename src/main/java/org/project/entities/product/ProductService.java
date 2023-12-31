package org.project.entities.product;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.project.entities.shop.Shop;
import org.project.entities.shop.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopRepository shopRepository;

    @Transactional
    public Product add(Long shopId, ProductDTO productDTO) {
        if (productDTO == null) {
            throw new IllegalArgumentException("ProductDTO cannot be null");
        }

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new EntityNotFoundException("Shop not found"));

        Product product = convertToEntity(productDTO);
        product.addShop(shop);

        try {
            shopRepository.save(shop);
            return productRepository.save(product);

        } catch (DataAccessException e) {
            throw new ServiceException("Error saving product", e);
        }
    }

    private Product convertToEntity(ProductDTO productDTO) {
        return new Product(
                productDTO.getCategory(),
                productDTO.getName(),
                productDTO.getManufacturer(),
                productDTO.getPrice(),
                productDTO.getQuantity()
        );
    }

    @Transactional
    public Product update(Long id, ProductDTO productDTO) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (productDTO == null) {
            throw new IllegalArgumentException("ProductDTO cannot be null");
        }

        Product product = this.findById(id);

        Category category = productDTO.getCategory();
        if (category != null) {
            product.setCategory(category);
        }

        String name = productDTO.getName();
        if (name != null && !name.isEmpty()) {
            product.setName(name);
        }

        String manufacturer = productDTO.getManufacturer();
        if (manufacturer != null && !manufacturer.isEmpty()) {
            product.setManufacturer(manufacturer);
        }

        Double price = productDTO.getPrice();
        product.setPrice(price);

        int quantity = productDTO.getQuantity();
        product.setQuantity(quantity);

        try {
            return productRepository.save(product);
        } catch (DataAccessException e) {
            throw new ServiceException("Error updating product", e);
        }
    }

    @Transactional
    public void delete(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        try {
            productRepository.delete(product);
        } catch (DataAccessException e) {
            throw new ServiceException("Error deleting product", e);
        }
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        try {
            return productRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            String.format("Product with ID %d not found", id)));
        } catch (DataAccessException e) {
            throw new ServiceException("Error finding product", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        try {
            return productRepository.findAll();
        } catch (DataAccessException e) {
            throw new ServiceException("Error finding all products", e);
        }
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    String.format("Product with ID %d not found", id));
        }

        try {
            productRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new ServiceException("Error deleting product", e);
        }
    }

    @Transactional
    public void deleteAll() {
        try {
            productRepository.deleteAll();
        } catch (DataAccessException e) {
            throw new ServiceException("Error deleting all products", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByCategory(String category) {
        try {
            return productRepository.findAllByCategory(Category.valueOf(category.toUpperCase()));
        } catch (DataAccessException e) {
            throw new ServiceException("Error finding products by name", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        try {
            List<Product> products = productRepository.findAllByName(name);
            if (products.isEmpty()) {
                throw new EntityNotFoundException(
                        String.format("No Products found with name %s", name));
            }

            return products;
        } catch (DataAccessException e) {
            throw new ServiceException("Error finding products by name", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByManufacturer(String manufacturer) {
        if (manufacturer == null || manufacturer.isEmpty()) {
            throw new IllegalArgumentException("Manufacturer cannot be null or empty");
        }

        try {
            List<Product> products = productRepository.findAllByManufacturer(manufacturer);
            if (products.isEmpty()) {
                throw new EntityNotFoundException(
                        String.format("No Products found with manufacturer %s", manufacturer));
            }

            return products;
        } catch (DataAccessException e) {
            throw new ServiceException("Error finding products by manufacturer", e);
        }
    }
}