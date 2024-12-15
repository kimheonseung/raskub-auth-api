package com.raskub.api.auth.oauth2.grant.authorizationserver.service

import com.raskub.api.auth.oauth2.grant.authorizationserver.client.OAuth2ClientProperty
import com.raskub.api.auth.oauth2.grant.authorizationserver.client.OAuth2GoogleTokenClient
import com.raskub.api.auth.oauth2.grant.authorizationserver.model.OAuth2GoogleTokenRequest
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class OAuth2AuthorizationServerService(
    private val oAuth2ClientProperty: OAuth2ClientProperty,
    private val oAuth2GoogleTokenClient: OAuth2GoogleTokenClient,
) {
    private val log = KotlinLogging.logger {}

    fun authorizeGoogleToken(
        code: String,
    ) = oAuth2GoogleTokenClient.postToken(
        OAuth2GoogleTokenRequest(
            clientId = oAuth2ClientProperty.registration.google.clientId,
            clientSecret = oAuth2ClientProperty.registration.google.clientSecret,
            code = code,
            redirectUri = oAuth2ClientProperty.registration.google.redirectUri,
            authorizationGrantType = oAuth2ClientProperty.registration.google.authorizationGrantType,
        )
    ).also { log.info { "google token received: $it" } }
}
