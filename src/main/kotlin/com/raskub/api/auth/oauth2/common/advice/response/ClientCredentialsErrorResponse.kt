package com.raskub.api.auth.oauth2.common.advice.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ClientCredentialsErrorResponse(
    @JsonProperty("error_code")
    val code: Int,
    @JsonProperty("error_message")
    val message: String? = "",
)
