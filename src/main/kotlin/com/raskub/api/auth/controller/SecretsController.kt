package com.raskub.api.auth.controller

import com.raskub.api.auth.common.constant.URIConstant.Companion.BASE_URI_V1
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["$BASE_URI_V1/secrets"])
class SecretsController(
    @Value("\${spring.security.oauth2.client.registration.google.client-id}")
    private val oauth2ClientId: String,
    @Value("\${spring.security.oauth2.client.registration.google.client-secret}")
    private val oauth2ClientSecret: String,
    @Value("\${spring.security.oauth2.client.registration.google.redirect-uri}")
    private val oauth2RedirectUri: String,
    @Value("\${api-key.header.client-name}")
    private val apiKeyHeaderClientName: String,
    @Value("\${api-key.header.client-id}")
    private val apiKeyHeaderClientId: String,
    @Value("\${api-key.header.client-secret}")
    private val apiKeyHeaderClientSecret: String,
    @Value("\${api-key.services.common-api.client-id}")
    private val apiKeyCommonApiClientId: String,
    @Value("\${api-key.services.common-api.client-name}")
    private val apiKeyCommonApiClientName: String,
    @Value("\${api-key.services.common-api.client-secret}")
    private val apiKeyCommonApiClientSecret: String,
) {
    @GetMapping("/all")
    fun getSecrets() = mapOf(
        "spring.security.oauth2.client.registration.google.client-id" to oauth2ClientId,
        "spring.security.oauth2.client.registration.google.client-secret" to oauth2ClientSecret,
        "spring.security.oauth2.client.registration.google.redirect-uri" to oauth2RedirectUri,
        "api-key.header.client-name" to apiKeyHeaderClientName,
        "api-key.header.client-id" to apiKeyHeaderClientId,
        "api-key.header.client-secret" to apiKeyHeaderClientSecret,
        "api-key.services.common-api.client-id" to apiKeyCommonApiClientId,
        "api-key.services.common-api.client-name" to apiKeyCommonApiClientName,
        "api-key.services.common-api.client-secret" to apiKeyCommonApiClientSecret,
    )
}
