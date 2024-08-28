// domain.usecase.ArticleUseCasesTest
package com.example.emazon.domain.usecase;

import com.example.emazon.domain.exceptions.*;
import com.example.emazon.domain.model.Article;
import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.spi.IArticlePersistencePort;
import com.example.emazon.domain.spi.IBrandPersistencePort;
import com.example.emazon.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ArticleUseCasesTest {

    private IArticlePersistencePort articlePersistencePort;
    private ICategoryPersistencePort categoryPersistencePort;
    private IBrandPersistencePort brandPersistencePort;
    private ArticleUseCases articleUseCases;

    @BeforeEach
    void setUp() {
        articlePersistencePort = Mockito.mock(IArticlePersistencePort.class);
        categoryPersistencePort = Mockito.mock(ICategoryPersistencePort.class);
        brandPersistencePort = Mockito.mock(IBrandPersistencePort.class);
        articleUseCases = new ArticleUseCases(articlePersistencePort, categoryPersistencePort, brandPersistencePort);
    }

    @Test
    void testSaveArticle_ValidArticle_Success() {
        // Crear los objetos de prueba
        Category category = new Category(1L, "Deportivo", "Articulo deportivo");
        Brand brand = new Brand(2L, "Nike", "Zapatos nike");
        Article article = new Article(1L, "Zapatos Deportivos", "Zapatos ideales para correr", 10, 59.99, List.of(category), brand);

        // Configurar los mocks para devolver los objetos esperados
        when(categoryPersistencePort.getCategory(1L)).thenReturn(Optional.of(category));
        when(brandPersistencePort.findByName("Nike")).thenReturn(Optional.of(brand)); // Aquí se configura el mock para que devuelva la marca correcta

        // Ejecutar la prueba y verificar que no lanza excepción
        assertDoesNotThrow(() -> articleUseCases.saveArticle(article));

        // Verificar que se haya llamado al método de persistencia
        verify(articlePersistencePort, times(1)).saveArticle(article);
    }

    @Test
    void testSaveArticle_InvalidBrand_ShouldThrowBrandNotFoundException() {
        Category category = new Category(1L, "Deportivo", "Articulo deportivo");
        Brand brand = new Brand(1L, "Non-existent Brand", "Descripción no existente");
        Article article = new Article(1L, "Zapatos Deportivos", "Zapatos ideales para correr", 10, 59.99, List.of(category), brand);

        when(brandPersistencePort.findByName("Non-existent Brand")).thenReturn(Optional.empty());

        assertThrows(BrandNotFoundException.class, () -> articleUseCases.saveArticle(article));
    }

    @Test
    void testSaveArticle_MismatchedBrandData_ShouldThrowInvalidBrandDataException() {
        Category category = new Category(1L, "Deportivo", "Articulo deportivo");
        Brand brand = new Brand(1L, "Nike", "Descripción incorrecta");
        Article article = new Article(1L, "Zapatos Deportivos", "Zapatos ideales para correr", 10, 59.99, List.of(category), brand);

        Brand existingBrand = new Brand(1L, "Nike", "Marca deportiva");
        when(brandPersistencePort.findByName("Nike")).thenReturn(Optional.of(existingBrand));

        assertThrows(InvalidBrandDataException.class, () -> articleUseCases.saveArticle(article));
    }




    @Test
    void testSaveArticle_InvalidCategoryData_ThrowsInvalidCategoryDataException() {
        Category category = new Category(1L, "Deportivo", "Articulo deportivo");
        Brand brand = new Brand(1L, "Nike", "Marca deportiva");
        Article article = new Article(1L, "Zapatos Deportivos", "Zapatos ideales para correr", 10, 59.99, List.of(category), brand);

        Category incorrectCategory = new Category(1L, "Deporte", "Articulo");
        when(categoryPersistencePort.getCategory(1L)).thenReturn(Optional.of(incorrectCategory));
        when(brandPersistencePort.findByName("Nike")).thenReturn(Optional.of(brand));

        assertThrows(InvalidCategoryDataException.class, () -> articleUseCases.saveArticle(article));
    }

    @Test
    void testSaveArticle_CategoryNotFound_ThrowsCategoryNotFoundException() {
        Category category = new Category(1L, "Deportivo", "Articulo deportivo");
        Brand brand = new Brand(1L, "Nike", "Marca deportiva");
        Article article = new Article(1L, "Zapatos Deportivos", "Zapatos ideales para correr", 10, 59.99, List.of(category), brand);

        when(categoryPersistencePort.getCategory(1L)).thenReturn(Optional.empty());
        when(brandPersistencePort.findByName("Nike")).thenReturn(Optional.of(brand));

        assertThrows(CategoryNotFoundException.class, () -> articleUseCases.saveArticle(article));
    }
}
