// infrastructure.configuration.BeanConfiguration
package com.example.emazon.infrastructure.configuration;

import com.example.emazon.domain.spi.ICategoryPersistencePort;
import com.example.emazon.domain.usecase.CategoryUseCases;
import com.example.emazon.domain.api.ICategoryServicePort;
import com.example.emazon.infrastructure.out.jpa.adapter.CategoryJpaAdapter;
import com.example.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.example.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCases(categoryPersistencePort());
    }
}
