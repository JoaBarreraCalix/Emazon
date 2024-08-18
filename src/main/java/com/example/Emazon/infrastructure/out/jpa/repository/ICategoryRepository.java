// infraestructure.out.jpa.repository.ICategoryRepository
package com.example.Emazon.infrastructure.out.jpa.repository;

import com.example.Emazon.infrastructure.out.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
}
