// application.mapper.BrandRequestMapper
package com.example.emazon.application.mapper;

import com.example.emazon.application.dto.BrandRequest;
import com.example.emazon.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandRequestMapper {
    Brand toBrand(BrandRequest brandRequest);
    BrandRequest toBrandRequest(Brand brand);
}
