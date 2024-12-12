package com.raskub.api.auth.oauth2.common.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.security.oauth2.client")
data class OAuth2ClientProperty(
    val registration: Registration,
    val provider: Provider,
) {
    data class Registration(
        val google: Google,
    ) {
        data class Google(
            val clientId: String,
            val clientSecret: String,
            val redirectUri: String,
            val authorizationGrantType: String,
            val scope: List<String>,
        )
    }
    data class Provider(
        val google: Google,
    ) {
        data class Google(
            val authorizationUri: String,
            val tokenUri: String,
            val userInfoUri: String,
            val userNameAttribute: String,
        )
    }
}
