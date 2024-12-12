package com.raskub.api.auth.apikey.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "api-key")
data class ApiKeyProperties(
    val header: Header = Header(),
    val services: Services = Services(),
) {
    data class Header(
        var clientName: String = "default-client-name",
        var clientId: String = "default-client-id",
        var clientSecret: String = "default-client-secret",
    )

    data class Services(
        val commonApi: CommonApi = CommonApi(),
    )

    data class CommonApi(
        var clientName: String = "default-common-client-name",
        var clientId: String = "default-common-client-id",
        var clientSecret: String = "default-common-client-secret",
    )
}
