package com.example.emazon.domain.usecase;

import com.example.emazon.domain.exceptions.*;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryUseCasesTest {

    private ICategoryPersistencePort categoryPersistencePort;
    private CategoryUseCases categoryUseCases;

    @BeforeEach
    void setUp() {
        categoryPersistencePort = Mockito.mock(ICategoryPersistencePort.class);
        categoryUseCases = new CategoryUseCases(categoryPersistencePort);
    }

    @Test
    void testSaveCategory_ValidCategory_Success() {
        Category category = new Category(1L, "Electronics", "All kinds of electronic gadgets and devices.");

        when(categoryPersistencePort.findByName("Electronics")).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> categoryUseCases.saveCategory(category));

        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void testSaveCategory_DuplicateCategory_ThrowsCategoryAlreadyExistsException() {
        Category category = new Category(1L, "Electronics", "All kinds of electronic gadgets and devices.");

        when(categoryPersistencePort.findByName("Electronics")).thenReturn(Optional.of(category));

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCases.saveCategory(category));

        verify(categoryPersistencePort, never()).saveCategory(any(Category.class));
    }

    @Test
    void testSaveCategory_NameTooLong_ThrowsCategoryNameTooLongException() {
        Category category = new Category(1L, "A very long category name that exceeds the limit of fifty characters", "Valid description");

        assertThrows(CategoryNameTooLongException.class, () -> categoryUseCases.saveCategory(category));
    }


    @Test
    void testSaveCategory_DescriptionTooLong_ThrowsCategoryDescriptionTooLongException() {
        Category category = new Category(1L, "Valid Name",
                "A very long description that exceeds the limit of ninety characters, making it invalidsdfasfdfasfsdfasfsdafsafafdasfasdfsafsdfasd.");

        assertThrows(CategoryDescriptionTooLongException.class, () -> categoryUseCases.saveCategory(category));
    }

    @Test
    void testGetCategory_ExistingCategory_Success() {
        Category category = new Category(1L, "Electronics", "All kinds of electronic gadgets and devices.");

        when(categoryPersistencePort.getCategory(1L)).thenReturn(Optional.of(category));

        Category foundCategory = categoryUseCases.getCategory(1L);

        assertEquals("Electronics", foundCategory.getName());
        verify(categoryPersistencePort, times(1)).getCategory(1L);
    }

    @Test
    void testGetCategory_NonExistingCategory_ThrowsCategoryNotFoundException() {
        when(categoryPersistencePort.getCategory(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryUseCases.getCategory(1L));
    }

    @Test
    void testUpdateCategory_ValidCategory_Success() {
        Category category = new Category(1L, "Electronics", "Updated description");

        assertDoesNotThrow(() -> categoryUseCases.updateCategory(category));

        verify(categoryPersistencePort, times(1)).updateCategory(category);
    }

    @Test
    void testDeleteCategory_ExistingCategory_ThrowsCategoryNotFoundException() {
        when(categoryPersistencePort.getCategory(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryUseCases.deleteCategory(1L));
    }
}
