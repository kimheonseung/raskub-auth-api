package com.raskub.api.auth.oauth2.authorization.google.controller

import com.raskub.api.auth.common.constant.URIConstant
import com.raskub.api.auth.common.response.ApiResponse
import com.raskub.api.auth.oauth2.authorization.google.controller.request.AuthorizeGoogleTokenRequest
import com.raskub.api.auth.oauth2.authorization.google.service.AuthorizationGoogleService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(URIConstant.OAUTH2_BASE_URI_V1 + "/authorization/google")
class AuthorizationGoogleController(
    private val authorizationGoogleService: AuthorizationGoogleService,
) {
    @PostMapping("/token")
    fun authorizeGoogleToken(
        @RequestBody authorizeGoogleTokenRequest: AuthorizeGoogleTokenRequest,
    ) = ApiResponse(
        data = authorizationGoogleService.authorizeGoogleToken(authorizeGoogleTokenRequest.code)
    )
}
