// infrastructure.in.rest.ArticleRestController
package com.example.emazon.infrastructure.in.rest;

import com.example.emazon.application.dto.ArticleRequest;
import com.example.emazon.application.handler.IArticleHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleRestController {

    private final IArticleHandler articleHandler;

    @PostMapping("/")
    public ResponseEntity<Void> createArticle(@RequestBody ArticleRequest articleRequest) {
        articleHandler.saveArticle(articleRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
