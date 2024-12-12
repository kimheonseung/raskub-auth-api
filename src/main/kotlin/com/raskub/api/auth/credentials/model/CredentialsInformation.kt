package com.raskub.api.auth.credentials.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CredentialsInformation(
    @JsonProperty("client_name")
    val clientName: String,
    @JsonProperty("client_id")
    val clientId: String,
    @JsonProperty("client_secret")
    val clientSecret: String,
)
