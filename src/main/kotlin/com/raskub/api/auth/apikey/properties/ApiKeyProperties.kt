package com.raskub.api.auth.apikey.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "api-key")
data class ApiKeyProperties(
    val header: Header,
    val services: Services,
) {
    data class Header(
        val clientName: String,
        val clientId: String,
        val clientSecret: String,
    )

    data class Services(
        val commonApi: CommonApi,
    )

    data class CommonApi(
        val clientName: String,
        val clientId: String,
        val clientSecret: String,
    )
}
