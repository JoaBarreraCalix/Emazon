package com.example.emazon.domain.usecase;

import com.example.emazon.domain.api.IArticleServicePort;
import com.example.emazon.domain.model.Article;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.exceptions.*;
import com.example.emazon.domain.spi.IArticlePersistencePort;
import com.example.emazon.domain.spi.ICategoryPersistencePort;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ArticleUseCases implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;

    public ArticleUseCases(IArticlePersistencePort articlePersistencePort, ICategoryPersistencePort categoryPersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveArticle(Article article) {
        validateArticle(article);
        articlePersistencePort.saveArticle(article);
    }

    private void validateArticle(Article article) {

        if(article.getCategories() == null || article.getCategories().isEmpty()) {
            throw new ArticleMustHaveAtLeastOneCategoryException();
        }
        if(article.getCategories().size() > 3) {
            throw new ArticleCannotHaveMoreThanThreeCategoriesException();
        }

        Set<Long> categoryIds = new HashSet<>();
        for (Category category : article.getCategories()) {
            if (!categoryIds.add(category.getId())) {
                throw new ArticleCannotHaveDuplicateCategoriesException();
            }

            // Verifica si la categoría existe en la base de datos
            Category existingCategory = categoryPersistencePort.getCategory(category.getId())
                    .orElseThrow(() -> new CategoryNotFoundException());

            // Verifica que el nombre y descripción coincidan
            if (!existingCategory.getName().equals(category.getName()) ||
                    !existingCategory.getDescription().equals(category.getDescription())) {
                throw new InvalidCategoryDataException("Category ID " + category.getId() + " has invalid name or description");
            }
        }
    }
}