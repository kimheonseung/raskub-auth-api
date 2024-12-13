package com.raskub.api.auth.oauth2.grant.authorization.controller

import com.raskub.api.auth.common.constant.URIConstant
import com.raskub.api.auth.oauth2.grant.authorization.controller.request.AuthorizeGoogleTokenRequest
import com.raskub.api.auth.oauth2.grant.authorization.controller.response.OAuth2Response
import com.raskub.api.auth.oauth2.grant.authorization.service.OAuth2Service
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(URIConstant.BASE_URI_V1 + "/oauth2")
class OAuth2Controller(
    private val oAuth2Service: OAuth2Service,
) {
    @PostMapping("/authorization/google/token")
    fun authorizeGoogleToken(
        @RequestBody authorizeGoogleTokenRequest: AuthorizeGoogleTokenRequest,
    ) = OAuth2Response(
        data = oAuth2Service.authorizeGoogleToken(authorizeGoogleTokenRequest.code)
    )
}
