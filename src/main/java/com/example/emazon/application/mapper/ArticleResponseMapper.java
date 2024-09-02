package com.example.emazon.application.mapper;

import com.example.emazon.application.dto.ArticleResponse;
import com.example.emazon.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticleResponseMapper {
    @Mapping(source = "brand.name", target = "brandName")
    ArticleResponse toResponse(Article article);
    List<ArticleResponse> toResponseList(List<Article> articles);
}