package com.raskub.api.auth.oauth2.grant.authorizationserver.controller

import com.raskub.api.auth.common.constant.URIConstant
import com.raskub.api.auth.oauth2.grant.authorizationserver.controller.request.AuthorizeGoogleTokenRequest
import com.raskub.api.auth.oauth2.grant.authorizationserver.controller.response.OAuth2Response
import com.raskub.api.auth.oauth2.grant.authorizationserver.service.OAuth2AuthorizationServerService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(URIConstant.BASE_URI_V1 + "/oauth2/authorization-server")
class OAuth2AuthorizationServerController(
    private val oAuth2AuthorizationServerService: OAuth2AuthorizationServerService,
) {
    @PostMapping("/google/token")
    fun authorizeGoogleToken(
        @RequestBody authorizeGoogleTokenRequest: AuthorizeGoogleTokenRequest,
    ) = OAuth2Response(
        data = oAuth2AuthorizationServerService.authorizeGoogleToken(authorizeGoogleTokenRequest.code)
    )
}
