// application.handler.IArticleHandler
package com.example.emazon.application.handler;

import com.example.emazon.application.dto.ArticleRequest;
import com.example.emazon.application.dto.ArticleResponse;

import com.example.emazon.domain.utils.PageCustom;

public interface IArticleHandler {
    void saveArticle(ArticleRequest articleRequest);
    PageCustom<ArticleResponse> listArticles(int page, int size, String sortOrder, String sortBy);
    boolean articleExists(Long articleId);
}
