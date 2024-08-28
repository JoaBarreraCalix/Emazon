// domain.usecase.BrandUseCases
package com.example.emazon.domain.usecase;

import com.example.emazon.domain.api.IBrandServicePort;
import com.example.emazon.domain.exceptions.*;
import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.spi.IBrandPersistencePort;
import com.example.emazon.domain.utils.PageCustom;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BrandUseCases implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCases(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        validateBrand(brand);

        Optional<Brand> existingBrand = brandPersistencePort.findByName(brand.getName());
        if (existingBrand.isPresent()) {
            throw new BrandAlreadyExistsException();
        }

        brandPersistencePort.saveBrand(brand);
    }

    @Override
    public PageCustom<Brand> listBrands(int page, int size, String sortOrder) {
        List<Brand> brands = brandPersistencePort.findAllBrands();

        List<Brand> sortedBrands = brands.stream()
                .sorted((b1, b2) -> "asc".equalsIgnoreCase(sortOrder)
                        ? b1.getName().compareTo(b2.getName())
                        : b2.getName().compareTo(b1.getName()))
                .collect(Collectors.toList());

        int total = sortedBrands.size();
        List<Brand> paginatedBrands = sortedBrands.stream()
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());

        return new PageCustom<>(paginatedBrands, page, size, total);
    }

    private void validateBrand(Brand brand) {

    }
}
