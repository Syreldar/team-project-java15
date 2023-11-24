package org.project.entities.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Transactional(readOnly = true)
    List<Customer> findAllByName(String name);

    boolean existsByName(String name);
}
