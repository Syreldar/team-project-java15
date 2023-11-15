package org.project.customer;

import org.project.helpers.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @PostMapping
    public ResponseEntity<APIResponse<Customer>> add(@RequestBody Customer customer) {
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Invalid customer provided."));
        }

        Customer createdCustomer = customerService.add(customer);
        if (createdCustomer == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null, "Failed to add customer."));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new APIResponse<>(createdCustomer, "Customer created successfully."));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Customer>> update(@PathVariable Long id, @RequestBody CustomerDTO customer) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Invalid ID provided."));
        }

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Invalid customer provided."));
        }

        Customer updatedCustomer = customerService.update(id, customer);
        if (updatedCustomer == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new APIResponse<>(null, "Failed to update customer."));
        }

        return ResponseEntity.ok(
                new APIResponse<>(updatedCustomer, "Customer updated successfully."));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<APIResponse<Customer>> deleteById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Invalid ID provided."));
        }

        Customer customer = customerService.findById(id);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null, "Failed to retrieve customer."));
        }

        customerService.deleteById(id);
        return ResponseEntity.ok(
                new APIResponse<>(customer, "Customer deleted successfully."));
    }

    @DeleteMapping
    public ResponseEntity<APIResponse<Iterable<Customer>>> deleteAll() {
        Iterable<Customer> customers = customerService.findAll();
        if (!customers.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null, "No Customers found."));
        }

        customerService.deleteAll();
        return ResponseEntity.ok(
                new APIResponse<>(customers, "All Customers deleted successfully."));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponse<Customer>> findById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Invalid ID provided."));
        }

        Customer customer = customerService.findById(id);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null, "Customer not found."));
        }

        return ResponseEntity.ok(
                new APIResponse<>(customer, "Customer retrieved successfully."));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<APIResponse<Customer>> findByName(@PathVariable String name) {
        if (name == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>(null, "Invalid name provided."));
        }

        Customer customer = customerService.findByName(name);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null, "Customer not found."));
        }

        return ResponseEntity.ok(
                new APIResponse<>(customer, "Customer retrieved successfully."));
    }

    @GetMapping
    public ResponseEntity<APIResponse<Iterable<Customer>>> findAll() {
        Iterable<Customer> customers = customerService.findAll();
        if (!customers.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new APIResponse<>(null, "No Customers found."));
        }

        return ResponseEntity.ok(
                new APIResponse<>(customers, "Customers retrieved successfully."));
    }
}
