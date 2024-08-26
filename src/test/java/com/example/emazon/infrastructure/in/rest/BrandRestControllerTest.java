// infrastructure.in.rest.BrandRestControllerTest

package com.example.emazon.infrastructure.in.rest;

import com.example.emazon.application.handler.IBrandHandler;
import com.example.emazon.domain.exceptions.BrandAlreadyExistsException;
import com.example.emazon.domain.exceptions.InvalidBrandNameException;
import com.example.emazon.infrastructure.exceptionhandler.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BrandRestController.class)
@Import(GlobalExceptionHandler.class)  // Importa explícitamente el RestExceptionHandler
class BrandRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBrandHandler brandHandler;

    @Test
    void createBrandWithEmptyName_ShouldReturn400() throws Exception {
        // Simula que el handler lanza la excepción InvalidBrandNameException
        doThrow(new InvalidBrandNameException()).when(brandHandler).saveBrand(any());

        mockMvc.perform(post("/brands/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "",
                                    "description": "Sportswear and accessories."
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createDuplicateBrand_ShouldReturn409() throws Exception {
        // Simula que el handler lanza la excepción BrandAlreadyExistsException
        doThrow(new BrandAlreadyExistsException()).when(brandHandler).saveBrand(any());

        mockMvc.perform(post("/brands/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Nike",
                                    "description": "Sportswear and accessories."
                                }
                                """))
                .andExpect(status().isConflict());
    }
}
