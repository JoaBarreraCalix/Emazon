// domain.usecase.ArticleUseCasesTest
package com.example.emazon.domain.usecase;

import com.example.emazon.domain.exceptions.*;
import com.example.emazon.domain.model.Article;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.spi.IArticlePersistencePort;
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
    private ArticleUseCases articleUseCases;

    @BeforeEach
    void setUp() {
        articlePersistencePort = Mockito.mock(IArticlePersistencePort.class);
        categoryPersistencePort = Mockito.mock(ICategoryPersistencePort.class);
        articleUseCases = new ArticleUseCases(articlePersistencePort, categoryPersistencePort);
    }

    @Test
    void testSaveArticle_ValidArticle_Success() {
        Category category = new Category(1L, "Deportivo", "Articulo deportivo");
        Article article = new Article(1L, "Zapatos Deportivos", "Zapatos ideales para correr", 10, 59.99, List.of(category));

        when(categoryPersistencePort.getCategory(1L)).thenReturn(Optional.of(category));

        assertDoesNotThrow(() -> articleUseCases.saveArticle(article));

        verify(articlePersistencePort, times(1)).saveArticle(article);
    }

    @Test
    void testSaveArticle_NoCategories_ThrowsArticleMustHaveAtLeastOneCategoryException() {
        Article article = new Article(1L, "Zapatos Deportivos", "Zapatos ideales para correr", 10, 59.99, List.of());

        assertThrows(ArticleMustHaveAtLeastOneCategoryException.class, () -> articleUseCases.saveArticle(article));
    }

    @Test
    void testSaveArticle_MoreThanThreeCategories_ThrowsArticleCannotHaveMoreThanThreeCategoriesException() {
        Category category1 = new Category(1L, "Deportivo", "Articulo deportivo");
        Category category2 = new Category(2L, "Mujer", "Articulo para mujer");
        Category category3 = new Category(3L, "Hombre", "Articulo para hombre");
        Category category4 = new Category(4L, "Tenis", "Zapatos de tipo teni semideportivos");

        Article article = new Article(1L, "Zapatos Deportivos", "Zapatos ideales para correr", 10, 59.99, List.of(category1, category2, category3, category4));

        assertThrows(ArticleCannotHaveMoreThanThreeCategoriesException.class, () -> articleUseCases.saveArticle(article));
    }

    @Test
    void testSaveArticle_DuplicateCategories_ThrowsArticleCannotHaveDuplicateCategoriesException() {
        Category category = new Category(1L, "Deportivo", "Articulo deportivo");

        Article article = new Article(1L, "Zapatos Deportivos", "Zapatos ideales para correr", 10, 59.99, List.of(category, category));

        assertThrows(ArticleCannotHaveDuplicateCategoriesException.class, () -> articleUseCases.saveArticle(article));
    }

    @Test
    void testSaveArticle_InvalidCategoryData_ThrowsInvalidCategoryDataException() {
        Category category = new Category(1L, "Deportivo", "Articulo deportivo");
        Article article = new Article(1L, "Zapatos Deportivos", "Zapatos ideales para correr", 10, 59.99, List.of(category));

        Category incorrectCategory = new Category(1L, "Deporte", "Articulo");
        when(categoryPersistencePort.getCategory(1L)).thenReturn(Optional.of(incorrectCategory));

        assertThrows(InvalidCategoryDataException.class, () -> articleUseCases.saveArticle(article));
    }

    @Test
    void testSaveArticle_CategoryNotFound_ThrowsCategoryNotFoundException() {
        Category category = new Category(1L, "Deportivo", "Articulo deportivo");
        Article article = new Article(1L, "Zapatos Deportivos", "Zapatos ideales para correr", 10, 59.99, List.of(category));

        when(categoryPersistencePort.getCategory(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> articleUseCases.saveArticle(article));
    }
}
