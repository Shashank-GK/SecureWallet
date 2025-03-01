package com.ivoyant.secure_wallet.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI userWalletOpenAPI() {
        Info info=new Info()
                        .title("Secure Wallet - Credential Storage API")
                        .description("API for securely storing and retrieving encrypted user credentials.")
                        .version("1.0.0");
        return new OpenAPI().info(info);
    }
}
