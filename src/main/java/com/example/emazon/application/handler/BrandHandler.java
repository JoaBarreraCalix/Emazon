// application.handler.BrandHandler
package com.example.emazon.application.handler;

import com.example.emazon.application.dto.BrandRequest;
import com.example.emazon.application.mapper.BrandRequestMapper;
import com.example.emazon.domain.api.IBrandServicePort;
import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.utils.PageCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler {

    private final IBrandServicePort brandServicePort;
    private final BrandRequestMapper brandRequestMapper;

    @Override
    public void saveBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.saveBrand(brand);
    }

    @Override
    public PageCustom<BrandRequest> listBrands(int page, int size, String sortOrder) {
        PageCustom<Brand> brands = brandServicePort.listBrands(page, size, sortOrder);
        return new PageCustom<>(
                brands.getContent().stream()
                        .map(brandRequestMapper::toBrandRequest)
                        .toList(),
                brands.getPageNumber(),
                brands.getPageSize(),
                brands.getTotalElements()
        );
    }

}
