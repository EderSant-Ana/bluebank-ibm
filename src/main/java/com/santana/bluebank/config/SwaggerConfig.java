package com.santana.bluebank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /*
     * Objeto (Bean) para configuração da documentação do Swagger2:
     * Apis (define onde estão o controllers da minha api)
     * Paths (define quais os paths serão mapeados)
     * ApiInfo (Objeto ApiInfo)
     */
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.santana.bluebank.controller") )
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /*
    * Objeto com as informações da API:
    * Título, Descrição, Versão, Contato
    */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("BlueBankApi")
                .description("Projeto Gama Academy + IBM")
                .version("1.0.0")
                .contact(contact())
                .build();
    }

    /*
     * Objeto com as informações de contato:
     * Nome, URL, Email
     */
    private Contact contact(){
        return new Contact("Rebooted Developer","","");
    }

}
