// infrastructure.out.jpa.adapter.ArticleJpaAdapter
package com.example.emazon.infrastructure.out.jpa.adapter;

import com.example.emazon.domain.model.Article;
import com.example.emazon.domain.spi.IArticlePersistencePort;
import com.example.emazon.infrastructure.out.jpa.entity.ArticleEntity;
import com.example.emazon.infrastructure.out.jpa.entity.BrandEntity;
import com.example.emazon.infrastructure.out.jpa.mapper.ArticleEntityMapper;
import com.example.emazon.infrastructure.out.jpa.repository.IArticleRepository;
import com.example.emazon.infrastructure.out.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;
    private final IBrandRepository brandRepository; // Necesario para manejar la relación con Brand

    @Override
    public void saveArticle(Article article) {
        // Verifica si la marca ya existe en la base de datos
        BrandEntity brandEntity = brandRepository.findByName(article.getBrand().getName())
                .orElseThrow(() -> new RuntimeException("Brand not found in database"));

        // Asigna la marca existente al artículo
        ArticleEntity articleEntity = articleEntityMapper.toEntity(article);
        articleEntity.setBrand(brandEntity);

        articleRepository.save(articleEntity);
    }


}