//domain.spi.IArticlePersistencePort
package com.example.emazon.domain.spi;

import com.example.emazon.domain.model.Article;

import java.util.List;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
    List<Article> findAllArticles();
}
