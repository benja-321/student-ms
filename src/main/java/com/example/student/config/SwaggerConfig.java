package com.example.student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static Contact DEFAULT_CONTACT = new Contact("Everis Peru", "https://www.everis.com","EmailPruebaEveris@gmail.com");
    public static ApiInfo DEFAULT_API_INFO = new ApiInfo("Student Api Documentation", "Documentation for the student services", "1.0",
            "PREMIUM", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.student"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag("Student","Students of a university"));
    }
}
