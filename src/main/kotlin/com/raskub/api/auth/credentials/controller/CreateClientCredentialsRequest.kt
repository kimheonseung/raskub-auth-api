package com.raskub.api.auth.credentials.controller

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateClientCredentialsRequest(
    @JsonProperty("client_name")
    val clientName: String,
)
