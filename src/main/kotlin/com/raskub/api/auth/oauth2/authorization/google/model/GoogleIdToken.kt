package com.raskub.api.auth.oauth2.authorization.google.model

data class GoogleIdToken(
    val sub: String,
    val email: String,
    val name: String?,
    val pictureUrl: String?,
    val iatMillis: Long,
    val expMillis: Long,
)
