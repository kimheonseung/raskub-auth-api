package com.raskub.api.auth.oauth2.authorization.google.client.request

import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleTokenRequest(
    @JsonProperty("client_id")
    val clientId: String,
    @JsonProperty("client_secret")
    val clientSecret: String,
    @JsonProperty("code")
    val code: String,
    @JsonProperty("redirect_uri")
    val redirectUri: String,
    @JsonProperty("grant_type")
    val authorizationGrantType: String,
)
