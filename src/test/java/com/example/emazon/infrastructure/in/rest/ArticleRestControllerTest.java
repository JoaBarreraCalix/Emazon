package com.example.emazon.infrastructure.in.rest;

import com.example.emazon.application.dto.ArticleRequest;
import com.example.emazon.application.handler.IArticleHandler;
import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.model.Category;
import com.example.emazon.infrastructure.in.rest.ArticleRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IArticleHandler articleHandler;

    private ArticleRequest validArticleRequest;

    @BeforeEach
    void setUp() {
        Brand brand = new Brand(1L, "Nike", "Marca reconocida por sus innovadores zapatos deportivos.");
        Category category = new Category(1L, "Hombre", "Producto para hombre.");

        validArticleRequest = new ArticleRequest();
        validArticleRequest.setName("Nike Air Max");
        validArticleRequest.setDescription("Zapatos deportivos con amortiguación avanzada.");
        validArticleRequest.setQuantity(20);
        validArticleRequest.setPrice(150.0);
        validArticleRequest.setBrand(brand);
        validArticleRequest.setCategories(List.of(category));
    }

    @Test
    void createArticle_ShouldReturnCreatedStatus_WhenArticleIsValid() throws Exception {
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/articles/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Nike Air Max\",\"description\":\"Zapatos deportivos con amortiguación avanzada.\",\"quantity\":20,\"price\":150.0,\"categories\":[{\"id\":1,\"name\":\"Hombre\"}],\"brand\":{\"name\":\"Nike\"}}"))
                .andExpect(status().isCreated());
    }



}
