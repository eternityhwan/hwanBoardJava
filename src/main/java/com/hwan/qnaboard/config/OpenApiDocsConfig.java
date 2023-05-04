package com.hwan.qnaboard.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger springdoc-ui 구성 파일
 */

@Configuration
public class OpenApiDocsConfig {

    @Bean
    public OpenAPI getOpenApi() {
        Info info = new Info()
            .title("SpringSecurity + JWT")
            .version("v0.0.1")
            .description("로그인 기능을 구현한 게시판입니다.");
        return new OpenAPI()
            .info(info);
    }
}
