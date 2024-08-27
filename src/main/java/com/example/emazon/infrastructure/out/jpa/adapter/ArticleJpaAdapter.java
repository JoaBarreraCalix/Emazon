// infrastructure.out.jpa.adapter.ArticleJpaAdapter
package com.example.emazon.infrastructure.out.jpa.adapter;

import com.example.emazon.domain.model.Article;
import com.example.emazon.domain.spi.IArticlePersistencePort;
import com.example.emazon.infrastructure.out.jpa.entity.ArticleEntity;
import com.example.emazon.infrastructure.out.jpa.mapper.ArticleEntityMapper;
import com.example.emazon.infrastructure.out.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public void saveArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toEntity(article);
        articleRepository.save(articleEntity);
    }


}