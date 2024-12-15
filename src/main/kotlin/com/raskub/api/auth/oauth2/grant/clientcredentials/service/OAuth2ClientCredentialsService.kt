package com.raskub.api.auth.oauth2.grant.clientcredentials.service

import com.raskub.api.auth.oauth2.grant.clientcredentials.common.exception.ClientCredentialsAlreadyExistsException
import com.raskub.api.auth.oauth2.grant.clientcredentials.entity.ClientCredentials
import com.raskub.api.auth.oauth2.grant.clientcredentials.model.ClientCredentialsInformation
import com.raskub.api.auth.oauth2.grant.clientcredentials.provider.ClientCredentialsProvider
import com.raskub.api.auth.oauth2.grant.clientcredentials.repository.ClientCredentialsRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class OAuth2ClientCredentialsService(
    private val clientCredentialsRepository: ClientCredentialsRepository,
    private val clientCredentialsProvider: ClientCredentialsProvider,
    @Qualifier("clientSecretEncoder")
    private val clientSecretEncoder: PasswordEncoder,
) {
    private val log = KotlinLogging.logger { }

    fun createClientCredentialsByClientName(
        clientName: String,
    ): ClientCredentialsInformation {
        val clientId = clientCredentialsProvider.generateClientId()
        val clientSecret = clientCredentialsProvider.generateClientSecretByClientId(clientId)

        val clientCredentials = ClientCredentials(
            clientName = clientName,
            clientId = clientId,
            clientSecret = clientSecretEncoder.encode(clientSecret),
            expiryDays = 365,
        )

        log.info { "clientName: $clientName, clientId: $clientId, clientSecret: $clientSecret" }

        clientCredentialsRepository.findByClientName(clientName)?.let {
            throw ClientCredentialsAlreadyExistsException()
        }
        clientCredentialsRepository.save(clientCredentials)

        return ClientCredentialsInformation(
            clientName = clientName,
            clientId = clientId,
            clientSecret = clientSecret,
        )
    }
}
