// infrastructure.configuration.BeanConfiguration
package com.example.emazon.infrastructure.configuration;

import com.example.emazon.domain.api.IBrandServicePort;
import com.example.emazon.domain.api.ICategoryServicePort;
import com.example.emazon.domain.api.IArticleServicePort;

import com.example.emazon.domain.spi.IBrandPersistencePort;
import com.example.emazon.domain.spi.ICategoryPersistencePort;
import com.example.emazon.domain.spi.IArticlePersistencePort;

import com.example.emazon.domain.usecase.BrandUseCases;
import com.example.emazon.domain.usecase.CategoryUseCases;
import com.example.emazon.domain.usecase.ArticleUseCases;

import com.example.emazon.infrastructure.out.jpa.adapter.BrandJpaAdapter;
import com.example.emazon.infrastructure.out.jpa.adapter.CategoryJpaAdapter;
import com.example.emazon.infrastructure.out.jpa.adapter.ArticleJpaAdapter;

import com.example.emazon.infrastructure.out.jpa.mapper.BrandEntityMapper;
import com.example.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.example.emazon.infrastructure.out.jpa.mapper.ArticleEntityMapper;

import com.example.emazon.infrastructure.out.jpa.repository.IBrandRepository;
import com.example.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import com.example.emazon.infrastructure.out.jpa.repository.IArticleRepository;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;
    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCases(categoryPersistencePort());
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort() {
        return new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IBrandServicePort brandServicePort() {
        return new BrandUseCases(brandPersistencePort());
    }

    @Bean
    public IArticlePersistencePort articlePersistencePort() {
        return new ArticleJpaAdapter(articleRepository, articleEntityMapper);
    }

    @Bean
    public IArticleServicePort articleServicePort() {
        return new ArticleUseCases(articlePersistencePort(), categoryPersistencePort());
    }
}
