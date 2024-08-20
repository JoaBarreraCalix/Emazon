// infraestructure.in.rest.CategoryRestController
package com.example.emazon.infrastructure.in.rest;

import com.example.emazon.application.dto.CategoryRequest;
import com.example.emazon.application.handler.ICategoryHandler;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Operations related to categories")
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @Operation(summary = "Create a new category", description = "Creates a new category with a unique name and description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "409", description = "Category already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> createCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryHandler.saveCategory(categoryRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "List all categories", description = "Lists all categories with optional sorting and pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully", content = @Content(schema = @Schema(implementation = CategoryRequest.class))),
            @ApiResponse(responseCode = "404", description = "No categories found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<CategoryRequest>> getAllCategories() {
        return new ResponseEntity<>(categoryHandler.getAllCategories(), HttpStatus.OK);
    }

    @Operation(summary = "Get a category by ID", description = "Retrieve a single category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category retrieved successfully", content = @Content(schema = @Schema(implementation = CategoryRequest.class))),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryRequest> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryHandler.getCategory(id), HttpStatus.OK);
    }

    @Operation(summary = "Update a category", description = "Update an existing category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category updated successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @PutMapping("/")
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryHandler.updateCategory(categoryRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete a category", description = "Delete a category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryHandler.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "List all categories", description = "Lists all categories with optional sorting and pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully", content = @Content(schema = @Schema(implementation = CategoryRequest.class))),
            @ApiResponse(responseCode = "404", description = "No categories found", content = @Content)
    })
    @GetMapping("/paged")
    public ResponseEntity<Page<CategoryRequest>> listCategories(Pageable pageable, @RequestParam String sortOrder) {
        Page<CategoryRequest> categories = categoryHandler.listCategories(pageable, sortOrder);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
