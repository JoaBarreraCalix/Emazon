// infrastructure.out.jpa.mapper.BrandEntityMapper
package com.example.emazon.infrastructure.out.jpa.mapper;

import com.example.emazon.domain.model.Brand;
import com.example.emazon.infrastructure.out.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandEntityMapper {
    BrandEntity toEntity(Brand brand);
    Brand toBrand(BrandEntity brandEntity);
    List<Brand> toBrandList(List<BrandEntity> brandEntities);
}
