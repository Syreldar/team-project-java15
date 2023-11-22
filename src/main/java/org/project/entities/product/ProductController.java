package org.project.entities.product;

import jakarta.validation.Valid;
import org.project.helpers.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<APIResponse<Product>> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        try {
            Product addedProduct = productService.add(productDTO);
            return ResponseEntity.ok(
                    new APIResponse<>(addedProduct, "Product added successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to add product."));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Product>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO productDTO)
    {
        try {
            Product updatedProduct = productService.update(id, productDTO);
            return ResponseEntity.ok(
                    new APIResponse<>(updatedProduct,
                            String.format("Product with ID %d updated successfully.", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            String.format("Failed to update product with ID %d.", id)));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(null,
                            String.format("Product with ID %d deleted successfully.", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null,
                            String.format("Failed to delete product with ID %d.", id)));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponse<Product>> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.findById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(product,
                            String.format("Product with ID %d retrieved successfully.", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null,
                            String.format("Product with ID %d not found.", id)));
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<APIResponse<List<Product>>> getProductsByCategory(@PathVariable String category) {
        try {
            List<Product> products = productService.findAllByCategory(Category.valueOf(category));
            return ResponseEntity.ok(
                    new APIResponse<>(products,
                            String.format("Products in category %s retrieved successfully.", category)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, String.format("Failed to retrieve products in category %s.", category)));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<APIResponse<List<Product>>> getProductsByName(@PathVariable String name) {
        try {
            List<Product> products = productService.findAllByName(name);
            return ResponseEntity.ok(
                    new APIResponse<>(products,
                            String.format("Products with name %s retrieved successfully.", name)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, String.format("Failed to retrieve products with name %s.", name)));
        }
    }

    @GetMapping("/manufacturer/{manufacturer}")
    public ResponseEntity<APIResponse<List<Product>>> getProductsByManufacturer(@PathVariable String manufacturer) {
        try {
            List<Product> products = productService.findAllByManufacturer(manufacturer);
            return ResponseEntity.ok(
                    new APIResponse<>(products,
                            String.format("Products from manufacturer %s retrieved successfully.", manufacturer)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, String.format("Failed to retrieve products from manufacturer %s.", manufacturer)));
        }
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Product>>> getAllProducts() {
        try {
            List<Product> products = productService.findAll();
            return ResponseEntity.ok(
                    new APIResponse<>(products, "All products retrieved successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to retrieve products."));
        }
    }
}
