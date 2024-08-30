package com.example.emazon.application.mapper;

import com.example.emazon.application.dto.ArticleResponse;
import com.example.emazon.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleResponseMapper {
    ArticleResponse toResponse(Article article);
}