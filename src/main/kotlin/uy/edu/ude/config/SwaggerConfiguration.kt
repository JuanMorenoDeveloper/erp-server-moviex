package uy.edu.ude.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfiguration {
  @Bean
  fun api(): Docket {

    return Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors
            .basePackage("uy.edu.ude"))
        .paths(PathSelectors.any())
        .build().apiInfo(apiEndPointsInfo())
  }

  private fun apiEndPointsInfo(): ApiInfo {

    return ApiInfoBuilder().title("Spring Boot REST API")
        .description("Language Management REST API")
        .contact(Contact("Juan Moreno", "https://proitcsolution.com.ve", "earth001@gmail.com"))
        .version("1.0-SNAPSHOT")
        .build()
  }

}