// src/test/java/com/example/Emazon/infraestructure/out/jpa/adapter/CategoryJpaAdapterTest.java
package com.example.emazon.infrastructure.out.jpa.adapter;

import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.exceptions.CategoryAlreadyExistsException;
import com.example.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.example.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.example.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

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
}
