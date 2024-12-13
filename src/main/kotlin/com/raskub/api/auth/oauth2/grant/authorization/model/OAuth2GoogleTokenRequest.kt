package com.raskub.api.auth.oauth2.grant.authorization.model

import com.fasterxml.jackson.annotation.JsonProperty

data class OAuth2GoogleTokenRequest(
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
