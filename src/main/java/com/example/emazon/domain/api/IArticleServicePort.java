//domain.api.IArticleServicePort
package com.example.emazon.domain.api;

import com.example.emazon.domain.model.Article;
import com.example.emazon.domain.utils.PageCustom;

public interface IArticleServicePort {
    void saveArticle(Article article);
    PageCustom<Article> listArticles(int page, int size, String sortOrder, String sortBy);
}
