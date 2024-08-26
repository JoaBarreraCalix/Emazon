// application.handler.BrandHandler
package com.example.emazon.application.handler;

import com.example.emazon.application.dto.BrandRequest;
import com.example.emazon.application.mapper.BrandRequestMapper;
import com.example.emazon.domain.api.IBrandServicePort;
import com.example.emazon.domain.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler {

    private final IBrandServicePort brandServicePort;  // Asegúrate de inyectar IBrandServicePort
    private final BrandRequestMapper brandRequestMapper;

    @Override
    public void saveBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.saveBrand(brand);
    }
}
