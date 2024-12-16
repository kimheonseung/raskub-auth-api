package com.raskub.api.auth.oauth2.common.config

import com.raskub.api.auth.common.constant.URIConstant
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.time.Duration
import java.util.UUID

@Configuration
@EnableConfigurationProperties(OAuth2ClientProperty::class)
class OAuth2Config {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**") // 모든 엔드포인트 허용
                    .allowedOrigins("http://localhost:3000") // 허용할 도메인
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                    .allowedHeaders("*") // 허용할 헤더
                    .allowCredentials(true) // 인증 정보 허용 (쿠키 등)
            }
        }
    }
    private fun commonSecurityFilterChain(http: HttpSecurity): HttpSecurity {
        return http
            .cors { }
            .csrf { it.disable() }
    }

    @Bean(name = ["oAuth2AuthorizationSecurityFilterChain"])
    fun oAuth2AuthorizationSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        OAuth2AuthorizationServerConfigurer.authorizationServer()
        return commonSecurityFilterChain(http)
            .securityMatcher("${URIConstant.OAUTH2_BASE_URI_V1}/authorization/google/**")
            .authorizeHttpRequests {
                it.requestMatchers("${URIConstant.OAUTH2_BASE_URI_V1}/authorization/google/**").permitAll()
                it.anyRequest().authenticated()
            }
            .oauth2Login(Customizer.withDefaults())
            .build()
    }

    @Bean(name = ["oAuth2CredentialsSecurityFilterChain"])
    fun oAuth2CredentialsSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http)
        return http.build()
    }

    @Bean
    fun clientCredentialsTokenServerSettings(): AuthorizationServerSettings {
        return AuthorizationServerSettings.builder()
            .issuer("http://localhost:8080") // 인증 서버 주소
            .tokenEndpoint("${URIConstant.OAUTH2_BASE_URI_V1}/credentials/token")
            .build()
    }

    @Bean
    fun registeredClientRepository(): RegisteredClientRepository {
        val client = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("a959de29-ceda-4c39-9a4d-c486236b8802")
            .clientSecret("\$2a\$10\$SP3ZCzD6Dw1bcjjEhPB5seRrGKkPeSYlhY7FAIQbuoxr5HTn1ke9m")
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .scope("read")
            .tokenSettings(
                TokenSettings.builder()
                    .accessTokenTimeToLive(Duration.ofSeconds(30))
                    .refreshTokenTimeToLive(Duration.ofSeconds(60))
                    .build()
            )
            .build()

        return InMemoryRegisteredClientRepository(client)
    }
}
