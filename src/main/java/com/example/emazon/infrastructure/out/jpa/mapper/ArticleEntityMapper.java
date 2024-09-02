// infrastructure.out.jpa.mapper.ArticleEntityMapper
package com.example.emazon.infrastructure.out.jpa.mapper;

import com.example.emazon.domain.model.Article;
import com.example.emazon.domain.model.Category;
import com.example.emazon.infrastructure.out.jpa.entity.ArticleCategoryEntity;
import com.example.emazon.infrastructure.out.jpa.entity.ArticleEntity;
import com.example.emazon.infrastructure.out.jpa.entity.BrandEntity;
import com.example.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleEntityMapper {

    // Mapea CategoryEntity a Category
    Category mapCategoryEntityToCategory(CategoryEntity categoryEntity);

    // Mapea Category a CategoryEntity
    CategoryEntity mapCategoryToCategoryEntity(Category category);

    // Mapea BrandEntity a Brand (Dominio)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    com.example.emazon.domain.model.Brand mapBrandEntityToBrand(BrandEntity brandEntity);

    // Mapea Brand (Dominio) a BrandEntity
    BrandEntity mapBrandToEntity(com.example.emazon.domain.model.Brand brand);

    // Mapea ArticleEntity a Article (Dominio)
    @Mapping(source = "brand", target = "brand")  // Mapea la marca
    @Mapping(source = "articleCategories", target = "categories")  // Mapea las categorías
    Article toArticle(ArticleEntity articleEntity);

    // Mapea Article (Dominio) a ArticleEntity
    ArticleEntity toEntity(Article article);

    // Mapea una lista de ArticleEntity a una lista de Article
    List<Article> toArticleList(List<ArticleEntity> articleEntities);

    // Mapea las categorías desde ArticleCategoryEntities a Categories
    default List<Category> mapArticleCategoryEntitiesToCategories(List<ArticleCategoryEntity> articleCategoryEntities) {
        if (articleCategoryEntities == null) {
            return new ArrayList<>();
        }

        return articleCategoryEntities.stream()
                .map(articleCategoryEntity -> {
                    Category category = new Category();
                    category.setId(articleCategoryEntity.getCategory().getId());
                    category.setName(articleCategoryEntity.getCategory().getName());
                    category.setDescription(articleCategoryEntity.getCategory().getDescription());
                    return category;
                }).toList();
    }

    // Mapea las categorías a ArticleCategoryEntities para persistencia
    default List<ArticleCategoryEntity> mapCategoriesToArticleCategoryEntities(Article article, ArticleEntity articleEntity) {
        if (article.getCategories() == null) {
            return new ArrayList<>();
        }

        return article.getCategories().stream()
                .map(category -> {
                    ArticleCategoryEntity articleCategoryEntity = new ArticleCategoryEntity();
                    articleCategoryEntity.setArticle(articleEntity);
                    CategoryEntity categoryEntity = new CategoryEntity();
                    categoryEntity.setId(category.getId());
                    articleCategoryEntity.setCategory(categoryEntity);
                    return articleCategoryEntity;
                }).toList();
    }
}
