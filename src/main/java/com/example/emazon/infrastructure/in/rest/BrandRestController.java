// infrastructure.in.rest.BrandRestController
package com.example.emazon.infrastructure.in.rest;

import com.example.emazon.application.dto.BrandRequest;
import com.example.emazon.application.handler.IBrandHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@Tag(name = "Brands", description = "Operations related to brands")
public class BrandRestController {

    private final IBrandHandler brandHandler;

    @Operation(summary = "Create a new brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully"),
            @ApiResponse(responseCode = "409", description = "Brand already exists")
    })
    @PostMapping("/")
    public ResponseEntity<Void> createBrand(@RequestBody BrandRequest brandRequest) {
        brandHandler.saveBrand(brandRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
