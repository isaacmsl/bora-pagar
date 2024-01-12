package ufrn.imd.boraPagar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    
    @Bean
    OpenAPI customConfig() {
        return new OpenAPI().info(new Info()
                    .title("College Subject organizer API with Java 21, Spring Boot 3 and MongoDB")
                    .version("v1")
                    .description("A site to view which subjects your friends will sign up for.")
        );
    }
}
