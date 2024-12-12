package com.raskub.api.auth.credentials.service

import com.raskub.api.auth.credentials.entity.Credentials
import com.raskub.api.auth.credentials.model.CredentialsInformation
import com.raskub.api.auth.credentials.provider.CredentialsProvider
import com.raskub.api.auth.credentials.repository.CredentialsRepository
import com.raskub.api.auth.oauth2.common.exception.ClientCredentialsAlreadyExistsException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CredentialsService(
    private val credentialsRepository: CredentialsRepository,
    private val credentialsProvider: CredentialsProvider,
) {
    private val log = KotlinLogging.logger { }
    private val bCryptPasswordEncoder = BCryptPasswordEncoder()

    fun createClientCredentialsByClientName(
        clientName: String,
    ): CredentialsInformation {
        val clientId = credentialsProvider.generateClientId()
        val clientSecret = credentialsProvider.generateClientSecretByClientId(clientId)

        val credentials = Credentials(
            clientName = clientName,
            clientId = clientId,
            clientSecret = bCryptPasswordEncoder.encode(clientSecret),
            expiryDays = 365,
        )

        log.info { "clientName: $clientName, clientId: $clientId, clientSecret: $clientSecret" }

        credentialsRepository.findByClientName(clientName)?.let {
            throw ClientCredentialsAlreadyExistsException()
        }
        credentialsRepository.save(credentials)

        return CredentialsInformation(
            clientName = clientName,
            clientId = clientId,
            clientSecret = clientSecret,
        )
    }
}
