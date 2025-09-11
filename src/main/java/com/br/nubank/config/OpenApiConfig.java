package com.br.nubank.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Desafio Nubank")
                                .version("v1")
                                .contact(new Contact()
                                        .email("matheusbsi1992@gmail.com")
                                        .url("https://www.linkedin.com/in/matheusandradesys/"))
                                .description("Uso de API para o desafio-NUbank")
                                .license(
                                        new License()
                                                .name("Apache 2.0")
                                                .url("https://github.com/matheusbsi1992/")))
                ;
    }

}
