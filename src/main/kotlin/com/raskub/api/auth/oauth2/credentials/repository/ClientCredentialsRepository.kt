package com.raskub.api.auth.oauth2.credentials.repository

import com.raskub.api.auth.oauth2.credentials.entity.ClientCredentials
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientCredentialsRepository : JpaRepository<ClientCredentials, Long> {
    fun findByClientName(clientName: String): ClientCredentials?
}
