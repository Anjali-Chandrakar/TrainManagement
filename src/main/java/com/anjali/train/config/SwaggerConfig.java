package com.anjali.train.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
@EnableSwagger2
@Configuration
public class SwaggerConfig {
   @Bean
   public Docket productApi() {
       return new Docket(DocumentationType.SWAGGER_2)
               .select()
               .apis(RequestHandlerSelectors.basePackage("com.anjali.train"))
               .paths(regex("/irctc-api/train/.*"))
               .build()
               .apiInfo(metaInfo());
   }
   private ApiInfo metaInfo() {
       ApiInfo apiInfo = new ApiInfo(
               "Demo IRCTC System\n Train Microservice",
               "This is a demo application implementing the basic features of Train Microservice "
               + " TERADATA INDIA PVT. LTD, PUNE, MAHARASHTRA, INDIA",
               "1.0",
               "Terms of Service:TERADATA INDIA PVT. LTD",
               new Contact("Anjali Chandrakar","7898390234",
                       "anjali.chandrakar@teradata.com"),
               "New Hire Assignment",
               "https://www.teradata.com/"
       );
       return apiInfo;
   }
}
