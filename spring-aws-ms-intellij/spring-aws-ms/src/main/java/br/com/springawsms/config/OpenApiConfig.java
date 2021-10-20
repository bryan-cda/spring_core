package br.com.springawsms.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    public Info apiInfo(){
        return new Info()
                .title("Sample RestFul API")
                .description("This is a sample Spring Boot RESTful service to book delivery information access")
                .version("v1")
                .termsOfService("Access github.com/bryan-cda")
                .contact(new Contact()
                        .name("Github")
                        .url("github.com/bryan-cda")
                )
                .license(new License()
                        .name("GPU")
                        .url("https://www.gnu.org/licenses/gpl-3.0.html"));
    }
}
