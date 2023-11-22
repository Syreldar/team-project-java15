package org.project.entities.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByName(String name);

    List<Product> findAllByManufacturer(String manufacturer);
}
