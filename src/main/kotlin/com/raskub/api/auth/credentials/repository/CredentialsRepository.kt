package com.raskub.api.auth.credentials.repository

import com.raskub.api.auth.credentials.entity.Credentials
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CredentialsRepository : JpaRepository<Credentials, Long> {
    fun findByClientName(clientName: String): Credentials?
    fun findByClientId(clientId: String): Credentials?
}
