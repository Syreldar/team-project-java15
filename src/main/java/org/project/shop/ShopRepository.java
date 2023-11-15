package org.project.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Iterable<Shop> findAllByName(String name);

    @Transactional
    void deleteByName(String name);

    @Transactional
    void deleteAllByName(String name);
}
