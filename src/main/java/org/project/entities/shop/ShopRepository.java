package org.project.entities.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Iterable<Shop> findAllByName(String name);

    @Transactional
    void deleteAllByName(String name);

    boolean existsByName(String name);
    List<Shop> findAllById(Iterable<Long> ids);

}
