package com.raskub.api.auth.oauth2.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(OAuth2ClientProperty::class)
class AuthorizationServerConfig(
    @Value("\${app.client-id}")
    val clientId: String,
    @Value("\${app.client-secret}")
    val clientSecret: String,
    @Value("\${app.redirect-uri}")
    val redirectUri: String,
)
