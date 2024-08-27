// ArticleRestControllerTest.java
package com.example.emazon.infrastructure.in.rest;

import com.example.emazon.infrastructure.exceptionhandler.GlobalExceptionHandler;
import com.example.emazon.application.handler.IArticleHandler;
import com.example.emazon.domain.exceptions.ArticleCannotHaveDuplicateCategoriesException;
import com.example.emazon.domain.exceptions.ArticleMustHaveAtLeastOneCategoryException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
@Import(GlobalExceptionHandler.class)  // Importar el GlobalExceptionHandler
class ArticleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IArticleHandler articleHandler;

    @Test
    void testCreateArticle_WithoutCategories_ShouldReturnBadRequest() throws Exception {
        // Configurar Mockito para lanzar la excepción cuando se llame a saveArticle con cualquier ArticleRequest
        doThrow(new ArticleMustHaveAtLeastOneCategoryException())
                .when(articleHandler).saveArticle(any());

        String jsonRequest = """
            {
                "name": "Zapatos Deportivos",
                "description": "Zapatos ideales para correr",
                "quantity": 10,
                "price": 59.99,
                "categories": []
            }
        """;

        mockMvc.perform(post("/articles/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateArticle_WithDuplicateCategories_ShouldReturnBadRequest() throws Exception {
        // Configurar Mockito para lanzar la excepción cuando se llame a saveArticle con cualquier ArticleRequest
        doThrow(new ArticleCannotHaveDuplicateCategoriesException())
                .when(articleHandler).saveArticle(any());

        String jsonRequest = """
            {
                "name": "Zapatos Deportivos",
                "description": "Zapatos ideales para correr",
                "quantity": 10,
                "price": 59.99,
                "categories": [
                    {
                        "id": 1,
                        "name": "Deportivo",
                        "description": "Articulo deportivo"
                    },
                    {
                        "id": 1,
                        "name": "Deportivo",
                        "description": "Articulo deportivo"
                    }
                ]
            }
        """;

        mockMvc.perform(post("/articles/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }
}
