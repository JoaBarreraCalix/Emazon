//domain.spi.IArticlePersistencePort
package com.example.emazon.domain.spi;

import com.example.emazon.domain.model.Article;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
}
