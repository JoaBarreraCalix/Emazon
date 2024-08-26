// infrastructure.out.jpa.adapter.BrandJpaAdapter
package com.example.emazon.infrastructure.out.jpa.adapter;

import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.spi.IBrandPersistencePort;
import com.example.emazon.infrastructure.out.jpa.entity.BrandEntity;
import com.example.emazon.infrastructure.out.jpa.mapper.BrandEntityMapper;
import com.example.emazon.infrastructure.out.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        BrandEntity brandEntity = brandEntityMapper.toEntity(brand);
        brandRepository.save(brandEntity);
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return brandRepository.findByName(name)
                .map(brandEntityMapper::toBrand);
    }
}
