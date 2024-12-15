package com.raskub.api.auth.oauth2.grant.clientcredentials.controller

import com.raskub.api.auth.common.constant.URIConstant
import com.raskub.api.auth.oauth2.grant.clientcredentials.controller.request.CreateClientCredentialsRequest
import com.raskub.api.auth.oauth2.grant.clientcredentials.controller.response.OAuth2ClientCredentialsResponse
import com.raskub.api.auth.oauth2.grant.clientcredentials.model.ClientCredentialsInformation
import com.raskub.api.auth.oauth2.grant.clientcredentials.service.OAuth2ClientCredentialsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(URIConstant.BASE_URI_V1 + "/oauth2/client-credentials")
class OAuth2ClientCredentialsController(
    private val oAuth2ClientCredentialsService: OAuth2ClientCredentialsService,
) {
    @PostMapping
    fun getByClientName(
        @RequestBody request: CreateClientCredentialsRequest,
    ): OAuth2ClientCredentialsResponse<ClientCredentialsInformation> =
        OAuth2ClientCredentialsResponse(data = oAuth2ClientCredentialsService.createClientCredentialsByClientName(request.clientName))
}
