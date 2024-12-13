package com.raskub.api.auth.oauth2.grant.authorization.model

import com.fasterxml.jackson.annotation.JsonProperty

data class OAuth2GoogleToken(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("expires_in")
    val expiresIn: Int,
    @JsonProperty("scope")
    val scope: String,
    @JsonProperty("token_type")
    val tokenType: String,
    @JsonProperty("id_token")
    val idToken: String,
)
