package org.project.entities.customer;

import org.project.helpers.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<APIResponse<Customer>> add(@RequestBody Customer customer) {
        try {
            Customer addedCustomer = customerService.add(customer);
            return ResponseEntity.ok(
                    new APIResponse<>(addedCustomer, "Customer added successfully."));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to add Customer."));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Customer>> update(@PathVariable Long id, @RequestBody CustomerDTO customer) {
        try {
            Customer updatedCustomer = customerService.update(id, customer);
            return ResponseEntity.ok(
                    new APIResponse<>(updatedCustomer,
                            String.format("Customer with ID %d updated successfully.", id)));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null,
                            String.format("Failed to update Customer with ID %d.", id)));
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<APIResponse<Customer>> deleteById(@PathVariable Long id) {
        try {
            customerService.deleteById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(null,
                            String.format("Customer with ID %d deleted successfully.", id)));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null,
                            String.format("Failed to delete Customer with ID %d.", id)));
        }
    }

    @DeleteMapping
    public ResponseEntity<APIResponse<Iterable<Customer>>> deleteAll() {
        try {
            customerService.deleteAll();
            return ResponseEntity.ok(
                    new APIResponse<>(null, "All Customers deleted successfully."));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to delete all Customers."));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponse<Customer>> findById(@PathVariable Long id) {
        try {
            Customer customer = customerService.findById(id);
            return ResponseEntity.ok(
                    new APIResponse<>(customer,
                            String.format("Customer with ID %d retrieved successfully.", id)));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null,
                            String.format("Customer with ID %d not found.", id)));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<APIResponse<Customer>> findByName(@PathVariable String name) {
        try {
            Customer customer = customerService.findByName(name);
            return ResponseEntity.ok(
                    new APIResponse<>(customer,
                            String.format("Customer with name %s retrieved successfully.", name)));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null,
                            String.format("Customer with name %s not found.", name)));
        }
    }

    @GetMapping
    public ResponseEntity<APIResponse<Iterable<Customer>>> findAll() {
        try {
            Iterable<Customer> customers = customerService.findAll();
            return ResponseEntity.ok(
                    new APIResponse<>(customers, "All Customers retrieved successfully."));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Failed to find all Customers."));
        }
    }
}
