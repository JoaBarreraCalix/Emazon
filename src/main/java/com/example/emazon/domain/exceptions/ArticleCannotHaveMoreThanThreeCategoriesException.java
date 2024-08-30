package com.example.emazon.domain.exceptions;

public class ArticleCannotHaveMoreThanThreeCategoriesException extends RuntimeException {
    public ArticleCannotHaveMoreThanThreeCategoriesException() {
        super("Article cannot have more than three categories");
    }
}
