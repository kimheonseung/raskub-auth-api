package com.raskub.api.auth.oauth2.authorization.google.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "google_oauth2_user", schema = "auth")
data class GoogleOAuth2User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(unique = true, nullable = false, updatable = false, name = "user_id")
    val userId: String? = null,

    @Column(unique = true, nullable = false, updatable = false, name = "sub")
    val sub: String,

    @Column(unique = true, nullable = false, updatable = false, name = "email")
    val email: String,

    @Column(name = "name")
    var name: String?,

    @Column(name = "picture_url")
    var pictureUrl: String?,
) {
    fun updateUserInformation(
        newName: String?,
        newPictureUrl: String?,
    ) {
        if (this.name != newName) {
            this.name = newName
        }

        if (this.pictureUrl != newPictureUrl) {
            this.pictureUrl = newPictureUrl
        }
    }
}
