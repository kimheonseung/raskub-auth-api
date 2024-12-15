package com.raskub.api.auth.oauth2.grant.clientcredentials.controller.request

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateClientCredentialsRequest(
    @JsonProperty("client_name")
    val clientName: String,
)
