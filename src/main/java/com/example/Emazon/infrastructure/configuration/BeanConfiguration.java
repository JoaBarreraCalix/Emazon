// infraestructure.configuration.BeanConfiguration
package com.example.Emazon.infrastructure.configuration;

import com.example.Emazon.domain.spi.ICategoryPersistencePort;
import com.example.Emazon.domain.usecase.CategoryUseCases;
import com.example.Emazon.domain.api.ICategoryServicePort;
import com.example.Emazon.infrastructure.out.jpa.adapter.CategoryJpaAdapter;
import com.example.Emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.example.Emazon.infrastructure.out.jpa.repository.ICategoryRepository;
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
