package org.project.entities.customer;

import jakarta.persistence.EntityNotFoundException;

import org.hibernate.service.spi.ServiceException;
import org.project.entities.cart.Cart;
import org.project.entities.cart.CartRepository;
import org.project.entities.order.OrderDTO;
import org.project.entities.order.OrderService;
import org.project.entities.product.Product;
import org.project.entities.shop.Shop;
import org.project.entities.shop.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShopRepository shopRepository;

    @Transactional
    public Customer add(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            throw new IllegalArgumentException("CustomerDTO cannot be null");
        }

        try {
            Customer customer = convertToEntity(customerDTO);
            return customerRepository.save(customer);
        } catch (DataAccessException e) {
            throw new ServiceException("Error updating customer", e);
        }
    }
    private Customer convertToEntity(CustomerDTO customerDTO) {
        return new Customer(
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getBalance(),
                customerDTO.getAddress(),
                customerDTO.getEmail()
        );
    }

    @Transactional
    public void buyProduct(Long customerId, Long shopId, OrderDTO orderDTO) {
        if (customerId == null || shopId == null || orderDTO == null) {
            throw new IllegalArgumentException("Customer ID, Shop ID, and OrderDTO cannot be null");
        }

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new EntityNotFoundException("Shop not found"));

        List<Product> productsToBuy = orderDTO.getProducts();

        boolean productsBelongToShop = new HashSet<>(shop.getProducts()).containsAll(productsToBuy);
        if (!productsBelongToShop) {
            throw new IllegalArgumentException("Not all products belong to the specified shop");
        }

        Cart cart = customer.getCarts().stream()
                .filter(c -> c.getShop().equals(shop))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for the specified shop"));

        cart.addProducts(productsToBuy);

        OrderDTO orderForShop = new OrderDTO(customerId, shopId, cart.getProducts());
        orderService.add(orderForShop);
        cart.clear();

        cartRepository.save(cart);
    }

    @Transactional
    public Customer update(Long id, CustomerDTO customerDTO) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (customerDTO == null) {
            throw new IllegalArgumentException("customerDTO cannot be null");
        }

        Customer customer = this.findById(id);

        String name = customerDTO.getFirstName();
        if (name != null && !name.isEmpty()) {
            customer.setName(name);
        }

        String lastName = customerDTO.getLastName();
        if (lastName != null && !lastName.isEmpty()) {
            customer.setLastName(lastName);
        }

        String address = customerDTO.getAddress();
        if (address != null && !address.isEmpty()) {
            customer.setAddress(address);
        }

        String email = customerDTO.getEmail();
        if (email != null && !email.isEmpty()) {
            customer.setEmail(email);
        }

        try {
            return customerRepository.save(customer);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error updating customer", e);
        }
    }

    @Transactional
    public void delete(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        try {
            customerRepository.delete(customer);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error deleting customer", e);
        }
    }

    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        try {
            return customerRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            String.format("Customer with id %d not found", id)));
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error deleting Customer with ID %d", id));
        }
    }

    @Transactional(readOnly = true)
    public Customer findByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }

        try {
            return customerRepository.findByName(name)
                    .orElseThrow(() -> new EntityNotFoundException(
                            String.format("Customer with name %s not found", name)));
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error finding Customer with name %s", name));
        }
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        try {
            return customerRepository.findAll();
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error finding all customers", e);
        }
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    String.format("Customer with ID %d not found", id));
        }

        try {
            customerRepository.deleteById(id);
        }
        catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error deleting Customer with ID %d", id));
        }
    }

    @Transactional
    public void deleteAll() {
        try {
            customerRepository.deleteAll();
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error deleting all customers", e);
        }
    }

    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }

        return customerRepository.existsByName(name);
    }
}
