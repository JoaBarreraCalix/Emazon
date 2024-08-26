package com.example.emazon.application.handler;

import com.example.emazon.application.dto.BrandRequest;
import com.example.emazon.application.mapper.BrandRequestMapper;
import com.example.emazon.domain.api.IBrandServicePort;
import com.example.emazon.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

class BrandHandlerTest {

    private IBrandServicePort brandServicePort;
    private BrandHandler brandHandler;
    private BrandRequestMapper brandRequestMapper;

    @BeforeEach
    void setUp() {
        brandServicePort = Mockito.mock(IBrandServicePort.class);
        brandRequestMapper = Mockito.mock(BrandRequestMapper.class);
        brandHandler = new BrandHandler(brandServicePort, brandRequestMapper);
    }

    @Test
    void testSaveBrand_Success() {
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Nike");
        brandRequest.setDescription("Sportswear and accessories.");

        Brand brand = new Brand(1L, "Nike", "Sportswear and accessories.");
        when(brandRequestMapper.toBrand(brandRequest)).thenReturn(brand);

        brandHandler.saveBrand(brandRequest);

        verify(brandServicePort, times(1)).saveBrand(brand);
    }
}
