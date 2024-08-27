// application.handler.ArticleHandler
package com.example.emazon.application.handler;

import com.example.emazon.application.dto.ArticleRequest;
import com.example.emazon.application.mapper.ArticleRequestMapper;
import com.example.emazon.domain.api.IArticleServicePort;
import com.example.emazon.domain.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler {

    private final IArticleServicePort articleServicePort;
    private final ArticleRequestMapper articleRequestMapper;

    @Override
    public void saveArticle(ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toArticle(articleRequest);
        articleServicePort.saveArticle(article);
    }
}
