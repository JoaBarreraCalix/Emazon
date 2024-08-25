// infrastructure.out.jpa.repository.IBrandRepository
package com.example.emazon.infrastructure.out.jpa.repository;

import com.example.emazon.infrastructure.out.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
    Optional<BrandEntity> findByName(String name);
}
