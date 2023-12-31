package org.project.entities.product;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.project.helpers.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/{shopId}")
    public ResponseEntity<APIResponse<Product>> add(
            @PathVariable Long shopId,
            @Valid @RequestBody ProductDTO productDTO) {
        try {
            Product addedProduct = productService.add(shopId, productDTO);
            return ResponseEntity.ok(
                    new APIResponse<>(addedProduct, "Product added to shop successfully."));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null, "Shop not found."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to add product to shop."));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Product>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO productDTO)
    {
        try {
            Product updatedProduct = productService.update(id, productDTO);
            return ResponseEntity.ok(
                    new APIResponse<>(updatedProduct,
                            String.format("Product with ID %d updated successfully.", id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null,
                            String.format("No Products found with id %d.", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            String.format("Failed to update product with ID %d.", id)));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteById(@PathVariable Long id) {
        try {
            productService.deleteById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(null,
                            String.format("Product with ID %d deleted successfully.", id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null,
                            String.format("No Products found with id %d.", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null,
                            String.format("Failed to delete product with ID %d.", id)));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponse<Product>> findById(@PathVariable Long id) {
        try {
            Product product = productService.findById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(product,
                            String.format("Product with ID %d retrieved successfully.", id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null,
                            String.format("No Products found with id %d.", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null,
                            String.format("Failed to retrieve product with ID %d.", id)));
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<APIResponse<List<Product>>> findAllByCategory(@PathVariable String category) {
        try {
            List<Product> products = productService.findAllByCategory(category);
            return ResponseEntity.ok(
                    new APIResponse<>(products,
                            String.format("Products in category %s retrieved successfully.", category)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null,
                            String.format("No Products found with category %s.", category)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null,
                            String.format("Failed to retrieve product with category %s.", category)));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<APIResponse<List<Product>>> findAllByName(@PathVariable String name) {
        try {
            List<Product> products = productService.findAllByName(name);
            return ResponseEntity.ok(
                    new APIResponse<>(products,
                            String.format("Products with name %s retrieved successfully.", name)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null,
                            String.format("No Products found with name %s.", name)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null,
                            String.format("Failed to retrieve product with name %s.", name)));
        }
    }

    @GetMapping("/manufacturer/{manufacturer}")
    public ResponseEntity<APIResponse<List<Product>>> findAllByManufacturer(@PathVariable String manufacturer) {
        try {
            List<Product> products = productService.findAllByManufacturer(manufacturer);
            return ResponseEntity.ok(
                    new APIResponse<>(products,
                            String.format("Products from manufacturer %s retrieved successfully.", manufacturer)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null,
                            String.format("No Products found with manufacturer %s.", manufacturer)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null,
                            String.format("Failed to retrieve products from manufacturer %s.", manufacturer)));
        }
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Product>>> findAll() {
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
