package org.project.services;
import org.project.repository.ProductRepository;
import org.project.models.Category;
import org.project.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Category category, String name, double price, int initialQuantity) {
        Product product = new Product(category, name, price, initialQuantity);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product updateProduct(Long productId, Category category, String name, String manufacturer, double price, int initialQuantity) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setCategory(category);
            product.setName(name);
            product.setManufacturer(manufacturer);
            product.setPrice(price);
            product.setInitialQuantity(initialQuantity);

            return productRepository.save(product);
        } else {
            // Prodotto non trovato, puoi gestire l'eccezione o restituire un valore predefinito.
            return null;
        }
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }
}