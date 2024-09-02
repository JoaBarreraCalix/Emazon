package com.example.emazon.domain.usecase;

import com.example.emazon.domain.exceptions.BrandNotFoundException;
import com.example.emazon.domain.model.Article;
import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.spi.IArticlePersistencePort;
import com.example.emazon.domain.spi.IBrandPersistencePort;
import com.example.emazon.domain.spi.ICategoryPersistencePort;
import com.example.emazon.domain.usecase.ArticleUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleUseCasesTest {

    @Mock
    private IArticlePersistencePort articlePersistencePort;

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private ArticleUseCases articleUseCases;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveArticle_ShouldSaveArticle_WhenValidArticle() {
        // Arrange
        Brand brand = new Brand(1L, "Nike", "Marca reconocida por sus innovadores zapatos deportivos.");
        Category category = new Category(1L, "Hombre", "Producto para hombre.");
        Article article = new Article(1L, "Nike Air Max", "Zapatos deportivos con amortiguación avanzada.", 20, 150.0, List.of(category), brand);

        when(brandPersistencePort.findByName(brand.getName())).thenReturn(Optional.of(brand));
        when(categoryPersistencePort.getCategory(category.getId())).thenReturn(Optional.of(category));

        // Act
        articleUseCases.saveArticle(article);

        // Assert
        verify(articlePersistencePort, times(1)).saveArticle(article);
    }

    @Test
    void saveArticle_ShouldThrowException_WhenBrandNotFound() {
        // Arrange
        Brand brand = new Brand(1L, "Nike", "Marca reconocida por sus innovadores zapatos deportivos.");
        Category category = new Category(1L, "Hombre", "Producto para hombre.");
        Article article = new Article(1L, "Nike Air Max", "Zapatos deportivos con amortiguación avanzada.", 20, 150.0, List.of(category), brand);

        when(brandPersistencePort.findByName(brand.getName())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BrandNotFoundException.class, () -> articleUseCases.saveArticle(article));
    }

    // Otros tests para validar categorías, paginación, etc.
}
