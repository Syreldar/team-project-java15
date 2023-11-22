package org.project.entities.shop;

import org.project.helpers.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @PostMapping("/add")
    public ResponseEntity<APIResponse<Shop>> add(@RequestBody Shop shop) {
        try {
            Shop addedShop = shopService.add(shop);
            return ResponseEntity.ok(
                    new APIResponse<>(addedShop, "Shop added successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to add Shop."));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Shop>> update(@PathVariable Long id, @RequestBody ShopDTO shopDTO) {
        try {
            Shop updatedShop = shopService.update(id, shopDTO);
            return ResponseEntity.ok(
                    new APIResponse<>(updatedShop,
                            String.format("Shop with ID %d updated successfully.", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            String.format("Failed to update Shop with ID %d.", id)));
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<APIResponse<Shop>> deleteById(@PathVariable Long id) {
        try {
            shopService.deleteById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(null,
                            String.format("Shop with ID %d deleted successfully.", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null,
                            String.format("Failed to delete Shop with ID %d.", id)));
        }
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<APIResponse<Shop>> deleteAllByName(@PathVariable String name) {
        try {
            shopService.deleteAllByName(name);
            return ResponseEntity.ok(
                    new APIResponse<>(null,
                            String.format("All shops with name %s deleted successfully.", name)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null,
                            String.format("Failed to delete shops with name %s.", name)));
        }
    }

    @DeleteMapping
    public ResponseEntity<APIResponse<Iterable<Shop>>> deleteAll() {
        try {
            shopService.deleteAll();
            return ResponseEntity.ok(
                    new APIResponse<>(null, "All Shops deleted successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to delete all Shops."));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponse<Shop>> findById(@PathVariable Long id) {
        try {
            Shop shop = shopService.findById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(shop,
                            String.format("Shop with ID %d retrieved successfully.", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null,
                            String.format("Shop with ID %d not found.", id)));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<APIResponse<Iterable<Shop>>> findAllByName(@PathVariable String name) {
        try {
            Iterable<Shop> shops = shopService.findAllByName(name);
            return ResponseEntity.ok(
                    new APIResponse<>(shops,
                            String.format("Shops with name %s retrieved successfully.", name)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null,
                            String.format("Shops with name %s not found.", name)));
        }
    }

    @GetMapping
    public ResponseEntity<APIResponse<Iterable<Shop>>> findAll() {
        try {
            Iterable<Shop> shops = shopService.findAll();
            return ResponseEntity.ok(
                    new APIResponse<>(shops, "All Shops retrieved successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to find all Shops."));
        }
    }
}