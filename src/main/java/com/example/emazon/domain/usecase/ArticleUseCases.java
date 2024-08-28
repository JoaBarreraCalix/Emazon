package com.example.emazon.domain.usecase;

import com.example.emazon.domain.api.IArticleServicePort;
import com.example.emazon.domain.model.Article;
import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.exceptions.*;
import com.example.emazon.domain.spi.IArticlePersistencePort;
import com.example.emazon.domain.spi.IBrandPersistencePort;
import com.example.emazon.domain.spi.ICategoryPersistencePort;

import java.util.HashSet;
import java.util.Set;

public class ArticleUseCases implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IBrandPersistencePort brandPersistencePort;

    public ArticleUseCases(IArticlePersistencePort articlePersistencePort,
                           ICategoryPersistencePort categoryPersistencePort,
                           IBrandPersistencePort brandPersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveArticle(Article article) {
        validateBusinessRules(article);
        articlePersistencePort.saveArticle(article);
    }

    private void validateBusinessRules(Article article) {
        validateBrand(article.getBrand());

        validateCategories(article);
    }

    private void validateBrand(Brand brand) {

        Brand existingBrand = brandPersistencePort.findByName(brand.getName())
                .orElseThrow(() -> new BrandNotFoundException("Brand not found in the database"));

        if (!existingBrand.getDescription().equals(brand.getDescription())) {
            throw new InvalidBrandDataException("Brand data does not match with the existing brand in the database");
        }
    }

    private void validateCategories(Article article) {
        Set<Long> categoryIds = new HashSet<>();
        for (Category category : article.getCategories()) {
            if (!categoryIds.add(category.getId())) {
                throw new ArticleCannotHaveDuplicateCategoriesException();
            }

            Category existingCategory = categoryPersistencePort.getCategory(category.getId())
                    .orElseThrow(() -> new CategoryNotFoundException());

            if (!existingCategory.getName().equals(category.getName()) ||
                    !existingCategory.getDescription().equals(category.getDescription())) {
                throw new InvalidCategoryDataException("Category ID " + category.getId() + " has invalid name or description");
            }
        }
    }
}
