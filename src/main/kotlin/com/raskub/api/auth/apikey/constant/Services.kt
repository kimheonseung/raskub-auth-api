package com.raskub.api.auth.apikey.constant

import com.raskub.api.auth.apikey.exception.UnknownServiceException

enum class Services(
    val clientName: String
) {
    COMMON_API("commonApi");
    companion object {
        fun byClientName(clientName: String): Services {
            return entries.find { it.clientName == clientName } ?: throw UnknownServiceException("Unknown service: $clientName")
        }
    }
}
