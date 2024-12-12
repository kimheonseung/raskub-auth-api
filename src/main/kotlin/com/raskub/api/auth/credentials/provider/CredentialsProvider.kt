package com.raskub.api.auth.credentials.provider

import org.springframework.stereotype.Component
import java.security.SecureRandom
import java.util.Base64
import java.util.UUID

@Component
class CredentialsProvider {
    fun generateClientId(): String = UUID.randomUUID().toString()

    fun generateClientSecretByClientId(
        clientId: String
    ): String {
        val randomSalt = ByteArray(32)
        SecureRandom().nextBytes(randomSalt)
        val secret = Base64.getUrlEncoder().withoutPadding().encodeToString(randomSalt)
        val combined = "$clientId:$secret"
        return Base64.getUrlEncoder().withoutPadding().encodeToString(combined.toByteArray())
    }
}
