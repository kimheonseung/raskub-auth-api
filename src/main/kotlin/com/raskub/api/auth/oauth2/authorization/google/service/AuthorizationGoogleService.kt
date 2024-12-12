package com.raskub.api.auth.oauth2.authorization.google.service

import com.raskub.api.auth.oauth2.authorization.google.client.GoogleTokenClient
import com.raskub.api.auth.oauth2.authorization.google.client.request.GoogleTokenRequest
import com.raskub.api.auth.oauth2.common.config.OAuth2ClientProperty
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class AuthorizationGoogleService(
    private val oAuth2ClientProperty: OAuth2ClientProperty,
    private val googleTokenClient: GoogleTokenClient,
) {
    private val log = KotlinLogging.logger {}

    fun authorizeGoogleToken(
        code: String,
    ) = googleTokenClient.postToken(
        GoogleTokenRequest(
            clientId = oAuth2ClientProperty.registration.google.clientId,
            clientSecret = oAuth2ClientProperty.registration.google.clientSecret,
            code = code,
            redirectUri = oAuth2ClientProperty.registration.google.redirectUri,
            authorizationGrantType = oAuth2ClientProperty.registration.google.authorizationGrantType,
        )
    ).also { log.info { "google token received: $it" } }
}
