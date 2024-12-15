package com.raskub.api.auth.oauth2.grant.clientcredentials.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ClientCredentialsInformation(
    @JsonProperty("client_name")
    val clientName: String,
    @JsonProperty("client_id")
    val clientId: String,
    @JsonProperty("client_secret")
    val clientSecret: String,
)
