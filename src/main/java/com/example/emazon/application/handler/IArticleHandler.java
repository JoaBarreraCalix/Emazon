// application.handler.IArticleHandler
package com.example.emazon.application.handler;

import com.example.emazon.application.dto.ArticleRequest;

public interface IArticleHandler {
    void saveArticle(ArticleRequest articleRequest);
}
