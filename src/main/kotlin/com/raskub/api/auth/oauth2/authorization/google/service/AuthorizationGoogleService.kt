package com.raskub.api.auth.oauth2.authorization.google.service

import com.raskub.api.auth.oauth2.authorization.google.client.GoogleTokenClient
import com.raskub.api.auth.oauth2.authorization.google.client.request.GoogleTokenRequest
import com.raskub.api.auth.oauth2.authorization.google.entity.GoogleOAuth2User
import com.raskub.api.auth.oauth2.authorization.google.model.GoogleIdToken
import com.raskub.api.auth.oauth2.authorization.google.parser.GoogleIdTokenParser
import com.raskub.api.auth.oauth2.common.config.OAuth2ClientProperty
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthorizationGoogleService(
    private val oAuth2ClientProperty: OAuth2ClientProperty,
    private val googleTokenClient: GoogleTokenClient,
    private val googleOAuth2UserReadService: GoogleOAuth2UserReadService,
    private val googleOAuth2UserWriteService: GoogleOAuth2UserWriteService,
    private val googleIdTokenParser: GoogleIdTokenParser,
) {
    private val log = KotlinLogging.logger {}

    fun authorizeGoogleToken(
        code: String,
    ): GoogleIdToken {
        val googleTokenResponse = googleTokenClient.postToken(
            GoogleTokenRequest(
                clientId = oAuth2ClientProperty.registration.google.clientId,
                clientSecret = oAuth2ClientProperty.registration.google.clientSecret,
                code = code,
                redirectUri = oAuth2ClientProperty.registration.google.redirectUri,
                authorizationGrantType = oAuth2ClientProperty.registration.google.authorizationGrantType,
            )
        )

        val googleIdToken = googleIdTokenParser.parseGoogleIdToken(googleTokenResponse.idToken)
        log.info { "google token information: $googleIdToken" }

        upsertGoogleOAuth2UserByGoogleIdToken(googleIdToken)

        // TODO: iat, exp 기준으로 토큰 캐시 저장하기?

        return googleIdToken
    }

    private fun upsertGoogleOAuth2UserByGoogleIdToken(
        googleIdToken: GoogleIdToken
    ) {
        val existGoogleOAuth2User = googleOAuth2UserReadService.findBySubOrNull(googleIdToken.sub)
        if (existGoogleOAuth2User == null) {
            val newGoogleOAuth2User = GoogleOAuth2User(
                userId = UUID.randomUUID().toString(),
                sub = googleIdToken.sub,
                email = googleIdToken.email,
                name = googleIdToken.name,
                pictureUrl = googleIdToken.pictureUrl,
            )
            googleOAuth2UserWriteService.save(newGoogleOAuth2User)
        } else {
            existGoogleOAuth2User.updateUserInformation(
                googleIdToken.name,
                googleIdToken.pictureUrl,
            )
            googleOAuth2UserWriteService.save(existGoogleOAuth2User)
        }
    }
}
