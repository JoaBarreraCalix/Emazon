package com.example.emazon.domain.model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    @Test
    void testValidCategory() {
        Category category = new Category(1L, "Electronics", "All kinds of electronic gadgets and devices.");
        assertEquals("Electronics", category.getName());
        assertEquals("All kinds of electronic gadgets and devices.", category.getDescription());
    }

    @Test
    void testInvalidCategoryNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Category(1L, "A very long name that exceeds the limit of fifty characters in total", "Valid description");
        });
    }

    @Test
    void testInvalidCategoryDescriptionTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Category(1L, "Valid Name", "A very long description that exceeds the limit of ninety characters in total, making it invalid.");
        });
    }
}