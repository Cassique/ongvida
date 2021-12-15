package com.ongvida.configuration;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@Profile({"!dev","!production"})
public class SwaggerConfig extends WebMvcConfigurationSupport {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String MANAGEMENT = "/management-api/.*";
    public static final String SECURE = "/secure-api/.*";

    @Bean
    public Docket productApi() {
        return new Docket( DocumentationType.SWAGGER_2)
                .groupName("OngVida")
                .select()
                .apis( RequestHandlerSelectors.basePackage("com.ongvida.controllers"))
                .paths( PathSelectors.any())
                .build()
                .apiInfo( metaData() );
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("ONG Vida  Educational REST API")
                .description("Detahamento da API para p sistema educacional da ONG Vida")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}