package com.example.emazon.domain.usecase;

import com.example.emazon.domain.exceptions.*;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.spi.ICategoryPersistencePort;
import com.example.emazon.domain.utils.PageCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    // Tests existentes

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
                "A very long description that exceeds the limit of ninety characters, making it invalid. blah blah blah extra text so that i can make the 90 char limit haha XD");

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

    // Nuevos tests

    @Test
    void testListCategories_Success() {
        Category category1 = new Category(1L, "Electronics", "Devices");
        Category category2 = new Category(2L, "Appliances", "Home appliances");

        when(categoryPersistencePort.findAllCategories()).thenReturn(Arrays.asList(category1, category2));

        PageCustom<Category> result = categoryUseCases.listCategories(0, 1, "asc");

        assertEquals(1, result.getContent().size());
        assertEquals("Appliances", result.getContent().get(0).getName());
        assertEquals(2, result.getTotalElements());
    }

    @Test
    void testListCategories_DescendingOrder() {
        Category category1 = new Category(1L, "Electronics", "Devices");
        Category category2 = new Category(2L, "Appliances", "Home appliances");

        when(categoryPersistencePort.findAllCategories()).thenReturn(Arrays.asList(category1, category2));

        PageCustom<Category> result = categoryUseCases.listCategories(0, 2, "desc");

        assertEquals(2, result.getContent().size());
        assertEquals("Electronics", result.getContent().get(0).getName());
        assertEquals("Appliances", result.getContent().get(1).getName());
    }

    @Test
    void testListCategories_Pagination() {
        Category category1 = new Category(1L, "Electronics", "Devices");
        Category category2 = new Category(2L, "Appliances", "Home appliances");
        Category category3 = new Category(3L, "Furniture", "Home furniture");

        when(categoryPersistencePort.findAllCategories()).thenReturn(Arrays.asList(category1, category2, category3));

        PageCustom<Category> result = categoryUseCases.listCategories(1, 2, "asc");

        assertEquals(1, result.getContent().size());
        assertEquals("Furniture", result.getContent().get(0).getName());
        assertEquals(3, result.getTotalElements());
    }

    @Test
    void testListCategories_Empty() {
        when(categoryPersistencePort.findAllCategories()).thenReturn(Collections.emptyList());

        PageCustom<Category> result = categoryUseCases.listCategories(0, 10, "asc");

        assertTrue(result.getContent().isEmpty());
        assertEquals(0, result.getTotalElements());
    }

    @Test
    void testListCategories_InvalidPage() {
        Category category1 = new Category(1L, "Electronics", "Devices");

        when(categoryPersistencePort.findAllCategories()).thenReturn(Collections.singletonList(category1));

        PageCustom<Category> result = categoryUseCases.listCategories(10, 10, "asc");

        assertTrue(result.getContent().isEmpty());
    }
}
