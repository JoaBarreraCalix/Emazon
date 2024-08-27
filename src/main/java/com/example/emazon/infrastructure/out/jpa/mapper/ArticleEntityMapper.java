// infrastructure.out.jpa.mapper.ArticleEntityMapper
package com.example.emazon.infrastructure.out.jpa.mapper;

import com.example.emazon.domain.model.Article;
import com.example.emazon.infrastructure.out.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleEntityMapper {
    ArticleEntity toEntity(Article article);
    Article toArticle(ArticleEntity articleEntity);
}
