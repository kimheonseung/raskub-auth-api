package com.raskub.api.auth.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.oidc.OidcScopes
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.time.Duration
import java.util.UUID

@Configuration
@EnableConfigurationProperties(OAuth2ClientProperty::class)
class AuthorizationServerConfig(
    @Value("\${app.client-id}")
    val clientId: String,
    @Value("\${app.client-secret}")
    val clientSecret: String,
    @Value("\${app.redirect-uri}")
    val redirectUri: String,
) {
    @Bean
    fun authorizationServerSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val authorizationServerConfigurer = OAuth2AuthorizationServerConfigurer.authorizationServer()
        return http
            .cors { }
            .csrf { it.disable() }
            .securityMatcher("/oauth2/authorization/app**")
            .authorizeHttpRequests {
                it.requestMatchers("/app/login", "/error", "/").permitAll()
                it.anyRequest().authenticated()
            }
            .with(authorizationServerConfigurer) { server -> server.oidc(Customizer.withDefaults()) } // openid 1.0
            .formLogin { it.loginPage("/app/login").permitAll() }
            .build()
    }

    @Bean
    fun oauth2ClientSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors { }
            .csrf { it.disable() }
            .securityMatcher("/oauth2/authorization/google**", "/api/oauth2/authorization/google/**", "/login/oauth2/code/google**")
            .authorizeHttpRequests {
                it.requestMatchers("/api/oauth2/authorization/google/**").permitAll()
                it.anyRequest().authenticated()
            }
            .oauth2Login(Customizer.withDefaults())
            .build()
    }

    @Bean
    fun registeredClientRepository(): RegisteredClientRepository {
        val client = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId(clientId)
            .clientSecret("{noop}$clientSecret")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri(redirectUri)
            .scope(OidcScopes.OPENID) // OpenID Connect 지원
            .clientSettings(
                ClientSettings.builder()
                    .requireAuthorizationConsent(true)
                    .build()
            )
            .tokenSettings(
                TokenSettings.builder()
                    .accessTokenTimeToLive(Duration.ofMinutes(30))
                    .refreshTokenTimeToLive(Duration.ofDays(7))
                    .build()
            )
            .build()

        return InMemoryRegisteredClientRepository(client)
    }
//
//    @Bean
//    fun providerSettings(): AuthorizationServerSettings {
//        return AuthorizationServerSettings.builder()
//            .issuer("http://localhost:8080") // 인증 서버의 호스트 주소
//            .authorizationEndpoint("/oauth2/authorization/app")
//            .tokenEndpoint("/oauth2/authorization/app/token")
//            .build()
//    }

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
}
