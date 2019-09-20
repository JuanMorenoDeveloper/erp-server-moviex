package uy.edu.ude

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc

@SpringBootApplication
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration::class)
class ErpMovieXServerApplication

fun main(args: Array<String>) {
  runApplication<ErpMovieXServerApplication>(*args)
}

