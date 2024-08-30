package com.example.emazon.domain.exceptions;

public class ArticleMustHaveAtLeastOneCategoryException extends RuntimeException {
    public ArticleMustHaveAtLeastOneCategoryException() {
        super("Article must have at least one category cannot be null ");
    }
}
