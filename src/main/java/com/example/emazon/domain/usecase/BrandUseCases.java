// domain.usecase.BrandUseCases
package com.example.emazon.domain.usecase;

import com.example.emazon.domain.api.IBrandServicePort;
import com.example.emazon.domain.exceptions.*;
import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.spi.IBrandPersistencePort;

import java.util.Optional;

public class BrandUseCases implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCases(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        validateBrand(brand);

        // Verificar si el nombre de la marca ya existe
        Optional<Brand> existingBrand = brandPersistencePort.findByName(brand.getName());
        if (existingBrand.isPresent()) {
            throw new BrandAlreadyExistsException();
        }

        brandPersistencePort.saveBrand(brand);
    }

    private void validateBrand(Brand brand) {
        if (brand.getName() == null || brand.getName().isEmpty()) {
            throw new InvalidBrandNameException();
        }
        if (brand.getDescription() == null || brand.getDescription().isEmpty()) {
            throw new InvalidBrandDescriptionException();
        }
        if (brand.getName().length() > 50) {
            throw new BrandNameTooLongException();
        }
        if (brand.getDescription().length() > 120) {
            throw new BrandDescriptionTooLongException();
        }
    }
}
