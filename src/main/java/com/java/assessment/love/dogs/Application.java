package com.java.assessment.love.dogs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources(value = {@PropertySource("classpath:./application.properties")})
public class Application {

  @Value("${api.version}")
  private String apiVersion;

  @Bean
  public OpenAPI apiInfo() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Dog Image API")
                .version(apiVersion)
                .description("API for managing dog breeds and images"));
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
