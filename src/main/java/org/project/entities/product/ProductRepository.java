package org.project.entities.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional(readOnly = true)
    List<Product> findAllByCategory(Category category);

    @Transactional(readOnly = true)
    List<Product> findAllByName(String name);

    @Transactional(readOnly = true)
    List<Product> findAllByManufacturer(String manufacturer);
}
