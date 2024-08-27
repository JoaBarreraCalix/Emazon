// infrastructure.in.rest.ArticleRestController
package com.example.emazon.infrastructure.in.rest;

import com.example.emazon.application.dto.ArticleRequest;
import com.example.emazon.application.handler.IArticleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@Tag(name = "Articles", description = "Operations related to articles")
public class ArticleRestController {

    private final IArticleHandler articleHandler;

    @Operation(summary = "Create a new article", description = "Creates a new article and associates it with one or more categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "409", description = "Category data does not match or duplicate categories found", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> createArticle(@RequestBody ArticleRequest articleRequest) {
        articleHandler.saveArticle(articleRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}