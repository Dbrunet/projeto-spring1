package com.example.postgresdemo.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by labtime on 11/06/18.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()//selecione
                .apis(RequestHandlerSelectors.basePackage("br.com.devdojo.controller"))//pacote dos controladores
//                .paths(regex("/v1.*"))
                .paths(regex("/.*"))//tudo
                .build()
                .globalOperationParameters(//parametros globais, para todos RestMapping
                        Collections.singletonList(new ParameterBuilder()
                        .name("Authorization")
                        .description("Auth token")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(true)
                        .build()))
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Porjeto Spring Boot By Diego")
                .description("A primeira base para outras versões de projetos back-end")
                .version("1.0")
                .contact(new Contact("Diego Brunet", "http://diegobrunet.com.br", "diegon.brunet@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/license/LICENSE-2.0")
                .build();
    }
}