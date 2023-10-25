package org.project.repository;

import org.project.models.Product;
import org.project.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByOwnerName(String ownerName);
    Optional<Shop> findById(Long id);
}
