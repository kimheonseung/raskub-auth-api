package com.raskub.api.auth.oauth2.authorization.google.client

import com.raskub.api.auth.oauth2.authorization.google.client.request.GoogleTokenRequest
import com.raskub.api.auth.oauth2.authorization.google.client.response.GoogleTokenResponse
import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "OAuth2GoogleTokenClient",
    url = "\${spring.security.oauth2.client.provider.google.token-uri}",
)
interface GoogleTokenClient {
    @PostMapping
    @Headers("Content-Type: application/json")
    fun postToken(request: GoogleTokenRequest): GoogleTokenResponse
}
