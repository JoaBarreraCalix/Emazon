package com.example.emazon.domain.usecase;

import com.example.emazon.domain.exceptions.*;
import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.spi.IBrandPersistencePort;
import com.example.emazon.domain.utils.PageCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandUseCasesTest {

    private IBrandPersistencePort brandPersistencePort;
    private BrandUseCases brandUseCases;

    @BeforeEach
    void setUp() {
        brandPersistencePort = Mockito.mock(IBrandPersistencePort.class);
        brandUseCases = new BrandUseCases(brandPersistencePort);
    }

    @Test
    void testSaveBrand_ValidBrand_Success() {
        Brand brand = new Brand(1L, "Nike", "Sportswear and accessories.");

        when(brandPersistencePort.findByName("Nike")).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> brandUseCases.saveBrand(brand));

        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }

    @Test
    void testSaveBrand_DuplicateBrand_ThrowsBrandAlreadyExistsException() {
        Brand brand = new Brand(1L, "Nike", "Sportswear and accessories.");

        when(brandPersistencePort.findByName("Nike")).thenReturn(Optional.of(brand));

        assertThrows(BrandAlreadyExistsException.class, () -> brandUseCases.saveBrand(brand));

        verify(brandPersistencePort, never()).saveBrand(any(Brand.class));
    }

    @Test
    void testSaveBrand_NameTooLong_ThrowsBrandNameTooLongException() {
        Brand brand = new Brand(1L, "A very long brand name that exceeds fifty characters", "Valid description");

        assertThrows(BrandNameTooLongException.class, () -> brandUseCases.saveBrand(brand));
    }

    @Test
    void testSaveBrand_DescriptionTooLong_ThrowsBrandDescriptionTooLongException() {
        Brand brand = new Brand(1L, "Nike", "A very long description that exceeds the limit of one hundred and twenty characters, making it invalid for this test case.");

        assertThrows(BrandDescriptionTooLongException.class, () -> brandUseCases.saveBrand(brand));
    }

    @Test
    void testSaveBrand_InvalidName_ThrowsInvalidBrandNameException() {
        Brand brand = new Brand(1L, "", "Sportswear and accessories.");

        assertThrows(InvalidBrandNameException.class, () -> brandUseCases.saveBrand(brand));
    }

    @Test
    void testSaveBrand_InvalidDescription_ThrowsInvalidBrandDescriptionException() {
        Brand brand = new Brand(1L, "Nike", "");

        assertThrows(InvalidBrandDescriptionException.class, () -> brandUseCases.saveBrand(brand));
    }

    @Test
    void listBrands_ShouldReturnPagedBrands() {
        // Arrange
        Brand brand1 = new Brand(1L, "Adidas", "Sportswear.");
        Brand brand2 = new Brand(2L, "Nike", "Sportswear and accessories.");

        // Configura el mock para devolver las marcas en el orden que deseas probar
        List<Brand> brands = Arrays.asList(brand1, brand2);
        when(brandPersistencePort.findAllBrands()).thenReturn(brands);

        // Act
        PageCustom<Brand> actualPage = brandUseCases.listBrands(0, 10, "asc");

        // Assert
        assertEquals("Adidas", actualPage.getContent().get(0).getName());
        assertEquals("Nike", actualPage.getContent().get(1).getName());
    }
}
