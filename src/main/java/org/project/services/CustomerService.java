package org.project.services;

import jakarta.persistence.EntityNotFoundException;

import org.project.models.Customer;
import org.project.models.dtos.CustomerDTO;
import org.project.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer update(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        String name = customerDTO.getFirstName();
        if (name != null) {
            customer.setName(name);
        }

        String lastName = customerDTO.getLastName();
        if (lastName != null) {
            customer.setLastName(lastName);
        }

        String address = customerDTO.getAddress();
        if (address != null) {
            customer.setAddress(address);
        }

        String email = customerDTO.getEmail();
        if (email != null) {
            customer.setEmail(email);
        }

        return customerRepository.save(customer);
    }

    @Transactional
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Customer findByName(String name) {
        return customerRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        customerRepository.deleteAll();
    }
}
