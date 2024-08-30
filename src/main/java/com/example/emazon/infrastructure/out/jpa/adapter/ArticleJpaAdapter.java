// infrastructure.out.jpa.adapter.ArticleJpaAdapter
package com.example.emazon.infrastructure.out.jpa.adapter;

import com.example.emazon.domain.model.Article;
import com.example.emazon.domain.spi.IArticlePersistencePort;
import com.example.emazon.infrastructure.out.jpa.entity.ArticleCategoryEntity;
import com.example.emazon.infrastructure.out.jpa.entity.ArticleEntity;
import com.example.emazon.infrastructure.out.jpa.entity.BrandEntity;
import com.example.emazon.infrastructure.out.jpa.mapper.ArticleEntityMapper;
import com.example.emazon.infrastructure.out.jpa.repository.IArticleRepository;
import com.example.emazon.infrastructure.out.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;

import java.util.List;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;
    private final IBrandRepository brandRepository;

    @Override
    public void saveArticle(Article article) {

        BrandEntity brandEntity = brandRepository.findByName(article.getBrand().getName())
                .orElseThrow(() -> new RuntimeException("Brand not found in database"));


        ArticleEntity articleEntity = articleEntityMapper.toEntity(article);

        List<ArticleCategoryEntity> articleCategoryEntities = articleEntityMapper.mapCategoriesToArticleCategoryEntities(article, articleEntity);
        articleEntity.setArticleCategories(articleCategoryEntities);

        articleEntity.setBrand(brandEntity);

        articleRepository.save(articleEntity);
    }

    @Override
    public List<Article> findAllArticles() {
        List<ArticleEntity> articleEntities = articleRepository.findAll();

        // Para forzar la carga de las relaciones de brand y categories
        articleEntities.forEach(article -> {
            Hibernate.initialize(article.getBrand());
            Hibernate.initialize(article.getCategories());
        });

        return articleEntityMapper.toArticleList(articleEntities);
    }


}