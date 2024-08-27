package com.example.emazon.domain.exceptions;

public class ArticleCannotHaveDuplicateCategoriesException extends RuntimeException {
    public ArticleCannotHaveDuplicateCategoriesException() {
      super("Article cannot have duplicate categories");
    }
}
