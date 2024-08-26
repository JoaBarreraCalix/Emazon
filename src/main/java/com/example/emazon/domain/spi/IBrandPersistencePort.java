// domain.spi.IBrandPersistencePort
package com.example.emazon.domain.spi;

import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.utils.PageCustom;

import java.util.List;
import java.util.Optional;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    Optional<Brand> findByName(String name);
    List<Brand> findAllBrands();
    PageCustom<Brand> listBrands(int page, int size, String sortOrder);
}
