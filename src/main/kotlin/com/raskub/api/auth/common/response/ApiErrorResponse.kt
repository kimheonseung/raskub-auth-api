package com.raskub.api.auth.common.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ApiErrorResponse(
    @JsonProperty("error_code")
    val code: Int,
    @JsonProperty("error_message")
    val message: String? = "",
)
