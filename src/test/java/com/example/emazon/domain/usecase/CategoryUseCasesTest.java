package com.example.emazon.domain.usecase;

import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CategoryUseCasesTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    private CategoryUseCases categoryUseCases;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryUseCases = new CategoryUseCases(categoryPersistencePort);
    }

    @Test
    void testListCategories() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Category> expectedPage = new PageImpl<>(Collections.singletonList(new Category(1L, "Electronics", "All kinds of electronic gadgets.")));

        when(categoryPersistencePort.listCategories(pageable, "asc")).thenReturn(expectedPage);

        Page<Category> result = categoryUseCases.listCategories(pageable, "asc");

        assertEquals(expectedPage, result);
    }
}
