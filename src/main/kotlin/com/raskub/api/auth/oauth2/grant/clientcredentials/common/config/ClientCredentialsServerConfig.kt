package com.raskub.api.auth.oauth2.grant.clientcredentials.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ClientCredentialsServerConfig {
    @Bean(name = ["clientSecretEncoder"])
    fun clientSecretEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
