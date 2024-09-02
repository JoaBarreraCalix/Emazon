// domain.usecase.BrandUseCases
package com.example.emazon.domain.usecase;

import com.example.emazon.common.Constants;
import com.example.emazon.domain.api.IBrandServicePort;
import com.example.emazon.domain.exceptions.*;
import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.spi.IBrandPersistencePort;
import com.example.emazon.domain.utils.PageCustom;

import java.util.List;
import java.util.Optional;

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
    public List<Brand> findAllBrands() {
        return brandPersistencePort.findAllBrands();
    }

    @Override
    public PageCustom<Brand> listBrands(int page, int size, String sortOrder) {
        List<Brand> brands = brandPersistencePort.findAllBrands();

        List<Brand> sortedBrands = brands.stream()
                .sorted((b1, b2) -> Constants.ASC_VALUE.equalsIgnoreCase(sortOrder)
                        ? b1.getName().compareTo(b2.getName())
                        : b2.getName().compareTo(b1.getName()))
                .toList();

        int total = sortedBrands.size();
        List<Brand> paginatedBrands = sortedBrands.stream()
                .skip((long) page * size)
                .limit(size)
                .toList();

        return new PageCustom<>(paginatedBrands, page, size, total);
    }

    private void validateBrand(Brand brand) {
        //ADD VALIDATIONS HERE
        //P.D MOST OF THEM HAVE BEEN MOVE TO THE DTO TO BE VALIDATED TROUGH JAKARTA

        // Validación del nombre de la marca
        if (brand.getName() == null || brand.getName().trim().isEmpty()) {
            throw new InvalidBrandNameException();
        }
        if (brand.getName().length() > Constants.BRAND_MAX_NAME_SIZE) {
            throw new BrandNameTooLongException();
        }

        // Validación de la descripción de la marca
        if (brand.getDescription() == null || brand.getDescription().trim().isEmpty()) {
            throw new InvalidBrandDescriptionException();
        }
        if (brand.getDescription().length() > Constants.BRAND_MAX_DESCRIPTION_SIZE) {
            throw new BrandDescriptionTooLongException();
        }
    }

}
