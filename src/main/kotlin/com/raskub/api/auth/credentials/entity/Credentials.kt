package com.raskub.api.auth.credentials.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDate

@Entity(name = "credentials")
data class Credentials(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "client_name", unique = true, nullable = false)
    var clientName: String? = null,

    @Column(name = "client_id", nullable = false)
    var clientId: String? = null,

    @Column(name = "client_secret", nullable = false)
    var clientSecret: String? = null,

    @Column(name = "client_secret_modified_date", nullable = false)
    var clientSecretModifiedDateTime: LocalDate = LocalDate.now(),

    @Column(name = "expiry_days", nullable = false)
    var expiryDays: Long? = null,
) {
    fun isClientSecretExpired(): Boolean = LocalDate.now()
        .isAfter(this.clientSecretModifiedDateTime.plusDays(this.expiryDays!!))

    fun changeClientSecret(
        newClientSecret: String
    ) {
        this.clientSecret = newClientSecret
        clientSecretModifiedDateTime = LocalDate.now()
    }
}
