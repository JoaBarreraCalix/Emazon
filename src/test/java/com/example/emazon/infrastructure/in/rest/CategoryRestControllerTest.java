package com.example.emazon.infrastructure.in.rest;

import com.example.emazon.application.dto.CategoryRequest;
import com.example.emazon.application.handler.ICategoryHandler;
import com.example.emazon.domain.utils.PageCustom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryRestController.class)
class CategoryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryHandler categoryHandler;

    @Test
    void testCreateCategory_Success() throws Exception {
        mockMvc.perform(post("/categories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "name": "Electronics",
                            "description": "All kinds of electronic gadgets and devices."
                        }
                        """))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAllCategories_Success() throws Exception {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Electronics");
        categoryRequest.setDescription("All kinds of electronic gadgets and devices.");

        when(categoryHandler.getAllCategories()).thenReturn(Collections.singletonList(categoryRequest));

        mockMvc.perform(get("/categories/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Electronics"))
                .andExpect(jsonPath("$[0].description").value("All kinds of electronic gadgets and devices."));
    }

    @Test
    void testGetCategoryById_Success() throws Exception {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Electronics");
        categoryRequest.setDescription("All kinds of electronic gadgets and devices.");

        when(categoryHandler.getCategory(1L)).thenReturn(categoryRequest);

        mockMvc.perform(get("/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Electronics"))
                .andExpect(jsonPath("$.description").value("All kinds of electronic gadgets and devices."));
    }

    @Test
    void testUpdateCategory_Success() throws Exception {
        mockMvc.perform(put("/categories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "name": "Electronics Updated",
                            "description": "Updated description."
                        }
                        """))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteCategory_Success() throws Exception {
        mockMvc.perform(delete("/categories/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testListCategories_Success() throws Exception {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Electronics");
        categoryRequest.setDescription("Devices");

        PageCustom<CategoryRequest> pageCustom = new PageCustom<>(Collections.singletonList(categoryRequest), 0, 10, 1);

        when(categoryHandler.listCategories(0, 10, "asc")).thenReturn(pageCustom);

        mockMvc.perform(get("/categories/paged")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sortOrder", "asc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Electronics"))
                .andExpect(jsonPath("$.content[0].description").value("Devices"));
    }
}
