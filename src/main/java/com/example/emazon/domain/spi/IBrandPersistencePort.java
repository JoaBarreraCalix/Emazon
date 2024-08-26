// domain.spi.IBrandPersistencePort
package com.example.emazon.domain.spi;

import com.example.emazon.domain.model.Brand;

import java.util.List;
import java.util.Optional;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    Optional<Brand> findByName(String name);
    List<Brand> findAllBrands();
}
