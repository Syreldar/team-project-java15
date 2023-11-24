package org.project.entities.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    @Transactional(readOnly = true)
    List<Shop> findAllById(Long id);

    @Transactional(readOnly = true)
    Iterable<Shop> findAllByName(String name);

    @Transactional
    void deleteAllByName(String name);

    boolean existsByName(String name);
}
