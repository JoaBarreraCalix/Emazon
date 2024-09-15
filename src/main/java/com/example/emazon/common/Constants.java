package com.example.emazon.common;

public class Constants {

    //EXCEPTIONS
    public static final String ARTICLE_NOT_NULL_NAME = "Name must not be empty";
    public static final int ARTICLE_MAX_NAME_SIZE= 50;
    public static final String ARTICLE_MAX_NAME_MESSAGE = "Name must not exceed 50 characters";
    public static final String ARTICLE_NOT_NULL_DESCRIPTION = "Description must not be empty";
    public static final int ARTICLE_MAX_DESCRIPTION_SIZE = 120;
    public static final String ARTICLE_MAX_DESCRIPTION_MESSAGE = "Description must not exceed 90 characters";
    public static final String ARTICLE_NOT_NEGATIVE_NUMBERS = "Value cannot be negative";
    public static final String ARTICLE_NOT_ZERO_VALUE = "Value cannot be zero";
    public static final String ARTICLE_NOT_NULL_CATEGORY = "Category must not be empty";
    public static final int ARTICLE_MIN_NUM_CATEGORIES = 1;
    public static final int ARTICLE_MAX_NUM_CATEGORIES = 3;
    public static final String ARTICLE_MAX_MIN_MESSAGE_CATEGORIES = "Categories cannot be less than 1 or greater than 3";
    public static final String ARTICLE_NOT_UNIQUE_CATEGORIES = "Categories cannot repeat";
    public static final String ARTICLE_NOT_NULL_BRAND = "Brand must not be empty";
    public static final String BRAND_NOT_NULL_NAME = "Brand must not be empty";
    public static final int BRAND_MAX_NAME_SIZE = 50;
    public static final String BRAND_MAX_NAME_MESSAGE = "Brand name must not exceed 50 characters";
    public static final String BRAND_NOT_NULL_DESCRIPTION = "Brand description must not be empty";
    public static final int BRAND_MAX_DESCRIPTION_SIZE = 120;
    public static final String BRAND_MAX_DESCRIPTION_MESSAGE = "Brand description must not exceed 120 characters";
    public static final String CATEGORY_NOT_NULL_NAME = "Name must not be empty";
    public static final int CATEGORY_MAX_NAME_SIZE = 50;
    public static final String CATEGORY_MAX_NAME_MESSAGE = "Name must not exceed 50 characters";
    public static final String CATEGORY_NOT_NULL_DESCRIPTION = "Description must not be empty";
    public static final int CATEGORY_MAX_DESCRIPTION_SIZE = 90;
    public static final String CATEGORY_MAX_DESCRIPTION_MESSAGE = "Description must not exceed 90 characters";

    public static final String UNEX_ERROR = "An unexpected error occurred: ";
    //VALUES
    public static final String ASC_VALUE = "asc";
    public static final String OPENAPI_TITLE = "Emazon API";
    public static final String OPENAPI_DESCRIPTION = "API documentation for Emazon project";
    public static final String OPENAPI_VERSION = "v1.0.0";
    public static final String OPENAPI_APACHE = "Apache 2.0";
    public static final String OPENAPI_SPRING_URL = "http://springdoc.org";
    public static final String OPENAPI_WIKI_DESCRIPTION = "Emazon Wiki Documentation";
    public static final String OPENAPI_WIKI_URL = "https://emazon.wiki.github.org/docs";


    public static final long TOKEN_EXPIRATION_TIME_IN_MILLI = 600000;
    public static final String TOKEN_CLAIM_ROLE = "role";
    public static final String TOKEN_SECRET_KEY = "byu45h90tg7qwcphuo234t-80NUOPG34H790345YKO[]asxnuio2345g90hwefp[";
    public static final String LOGIN_USER_NOT_FOUND = "User not found in database";
    public static final String LOGIN_AUTH_HEADER = "Authorization";
    public static final String TOKEN_IS_EXPIRED = "Token is expired";
    public static final String TOKEN_IS_INVALID = "Token is invalid";
    public static final String TOKEN_GENERAL_ERROR = "There is an error with the token";
    public static final String LOGIN_AUTH_HEADER_START = "Bearer ";
    public static final String LOGIN_ROLE_NOT_FOUND = "Role not found";
    public static final String AUX_BODEGA_RESTRAINT = "ADMIN";
    public static final String USER_DUP_EMAIL = "El correo ya está en uso";
    public static final String USER_DUP_DOCID = "El documento de identificación ya está en uso";
    public static final String USER_EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String USER_NUMBER_REGEX = "^\\+57\\d{10}$";


}
