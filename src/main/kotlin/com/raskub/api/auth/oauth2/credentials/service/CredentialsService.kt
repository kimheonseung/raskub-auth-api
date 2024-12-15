package com.raskub.api.auth.oauth2.credentials.service

import com.raskub.api.auth.oauth2.common.exception.ClientCredentialsAlreadyExistsException
import com.raskub.api.auth.oauth2.credentials.entity.ClientCredentials
import com.raskub.api.auth.oauth2.credentials.model.CredentialsInformation
import com.raskub.api.auth.oauth2.credentials.provider.CredentialsProvider
import com.raskub.api.auth.oauth2.credentials.repository.ClientCredentialsRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CredentialsService(
    private val clientCredentialsRepository: ClientCredentialsRepository,
    private val credentialsProvider: CredentialsProvider,
    @Qualifier("clientSecretEncoder")
    private val clientSecretEncoder: PasswordEncoder,
) {
    private val log = KotlinLogging.logger { }

    fun createClientCredentialsByClientName(
        clientName: String,
    ): CredentialsInformation {
        val clientId = credentialsProvider.generateClientId()
        val clientSecret = credentialsProvider.generateClientSecretByClientId(clientId)

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

        return CredentialsInformation(
            clientName = clientName,
            clientId = clientId,
            clientSecret = clientSecret,
        )
    }
}
