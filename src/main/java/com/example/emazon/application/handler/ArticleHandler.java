// application.handler.ArticleHandler
package com.example.emazon.application.handler;

import com.example.emazon.application.dto.ArticleRequest;
import com.example.emazon.application.dto.ArticleResponse;
import com.example.emazon.application.mapper.ArticleRequestMapper;
import com.example.emazon.application.mapper.ArticleResponseMapper;
import com.example.emazon.domain.api.IArticleServicePort;
import com.example.emazon.domain.model.Article;
import com.example.emazon.domain.spi.IArticlePersistencePort;
import com.example.emazon.domain.utils.PageCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler {

    private final IArticleServicePort articleServicePort;
    private final ArticleRequestMapper articleRequestMapper;
    private final ArticleResponseMapper articleResponseMapper;
    private final IArticlePersistencePort articlePersistencePort;

    @Override
    public void saveArticle(ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toArticle(articleRequest);
        articleServicePort.saveArticle(article);
    }

    @Override
    public PageCustom<ArticleResponse> listArticles(int page, int size, String sortOrder, String sortBy) {
        PageCustom<Article> articlePage = articleServicePort.listArticles(page, size, sortOrder, sortBy);
        List<ArticleResponse> articleResponses = articlePage.getContent().stream()
                .map(articleResponseMapper::toResponse)
                .toList();

        return new PageCustom<>(articleResponses, page, size, articlePage.getTotalElements());
    }

    @Override
    public boolean articleExists(Long articleId) {
        return articlePersistencePort.articleExistsById(articleId);
    }
}
