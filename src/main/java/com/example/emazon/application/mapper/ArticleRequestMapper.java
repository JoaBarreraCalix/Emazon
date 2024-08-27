// application.mapper.ArticleRequestMapper
package com.example.emazon.application.mapper;

import com.example.emazon.application.dto.ArticleRequest;
import com.example.emazon.domain.model.Article;
import com.example.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleRequestMapper {
    Article toArticle(ArticleRequest articleRequest);
}
