package org.project.services;

import org.project.models.Customer;
import org.project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(String firstName, String lastName, double balance) {
        Customer customer = new Customer(firstName, lastName, balance);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public Customer updateCustomer(Long customerId, String firstName, String lastName, double balance) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setBalance(balance);

            return customerRepository.save(customer);
        } else {
            // Cliente non trovato, puoi gestire l'eccezione o restituire un valore predefinito.
            return null;
        }
    }
}