package org.project.repositories;

import org.project.models.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Iterable<Shop> findAllByName(String name);

    void deleteByName(String name);

    void deleteAllByName(String name);
}
