package com.example.emazon.infrastructure.out.jpa.adapter;

import com.example.emazon.domain.model.Category;
import com.example.emazon.infrastructure.exception.CategoryAlreadyExistsException;
import com.example.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.example.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.example.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CategoryJpaAdapterTest {

    private ICategoryRepository categoryRepository;
    private CategoryEntityMapper categoryEntityMapper;
    private CategoryJpaAdapter categoryJpaAdapter;

    @BeforeEach
    void setUp() {
        categoryRepository = Mockito.mock(ICategoryRepository.class);
        categoryEntityMapper = Mockito.mock(CategoryEntityMapper.class);
        categoryJpaAdapter = new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Test
    void testSaveCategoryThrowsCategoryAlreadyExistsException() {
        Category category = new Category(1L, "Electronics", "All kinds of electronic gadgets and devices.");
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Electronics", "All kinds of electronic gadgets and devices.");

        when(categoryRepository.findByName("Electronics")).thenReturn(Optional.of(categoryEntity));

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryJpaAdapter.saveCategory(category));
    }

    @Test
    void testListCategoriesWithPaginationAndSorting() {
        Pageable pageable = PageRequest.of(0, 10);
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Electronics", "All kinds of electronic gadgets and devices.");
        Page<CategoryEntity> categoryEntities = new PageImpl<>(Collections.singletonList(categoryEntity));

        // Asegúrate de que el mock devuelva una instancia válida de Page<CategoryEntity>
        when(categoryRepository.findAll(any(Pageable.class))).thenReturn(categoryEntities);
        when(categoryEntityMapper.toCategory(categoryEntity)).thenReturn(new Category(1L, "Electronics", "All kinds of electronic gadgets and devices."));

        Page<Category> result = categoryJpaAdapter.listCategories(pageable, "asc");

        assertEquals(1, result.getTotalElements());
        assertEquals("Electronics", result.getContent().get(0).getName());
    }
}
