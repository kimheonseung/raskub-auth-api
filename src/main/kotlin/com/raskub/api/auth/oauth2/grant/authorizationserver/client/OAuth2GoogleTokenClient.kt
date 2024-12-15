package com.raskub.api.auth.oauth2.grant.authorizationserver.client

import com.raskub.api.auth.oauth2.grant.authorizationserver.model.OAuth2GoogleToken
import com.raskub.api.auth.oauth2.grant.authorizationserver.model.OAuth2GoogleTokenRequest
import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "OAuth2GoogleTokenClient",
    url = "\${spring.security.oauth2.client.provider.google.token-uri}",
)
interface OAuth2GoogleTokenClient {
    @PostMapping
    @Headers("Content-Type: application/json")
    fun postToken(request: OAuth2GoogleTokenRequest): OAuth2GoogleToken
}
