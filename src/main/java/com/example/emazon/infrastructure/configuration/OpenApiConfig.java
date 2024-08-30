//infrastructure.configuration.OpenApiConfig
package com.example.emazon.infrastructure.configuration;

import com.example.emazon.common.Constants;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(Constants.OPENAPI_TITLE)
                        .description(Constants.OPENAPI_DESCRIPTION)
                        .version(Constants.OPENAPI_VERSION)
                        .license(new License().name(Constants.OPENAPI_APACHE).url(Constants.OPENAPI_SPRING_URL)))
                .externalDocs(new ExternalDocumentation()
                        .description(Constants.OPENAPI_WIKI_DESCRIPTION)
                        .url(Constants.OPENAPI_WIKI_URL));
    }
}
