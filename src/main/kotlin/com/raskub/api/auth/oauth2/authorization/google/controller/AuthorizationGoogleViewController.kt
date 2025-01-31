package com.raskub.api.auth.oauth2.authorization.google.controller

import com.raskub.api.auth.common.constant.URIConstant
import com.raskub.api.auth.oauth2.authorization.google.service.AuthorizationGoogleService
import com.raskub.api.auth.oauth2.common.config.OAuth2ClientProperty
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@CrossOrigin("http://localhost:8080")
@RequestMapping(URIConstant.OAUTH2_VIEW_BASE_URI_V1 + "/authorization/google")
class AuthorizationGoogleViewController(
    private val authorizationGoogleService: AuthorizationGoogleService,
    private val oAuth2ClientProperty: OAuth2ClientProperty,
) {
    @GetMapping("/login")
    fun authorizeGoogleLoginPage(model: Model): String {
        model.addAttribute("GOOGLE_CLIENT_ID", oAuth2ClientProperty.registration.google.clientId)
        model.addAttribute("REDIRECT_URI", oAuth2ClientProperty.registration.google.redirectUri)
        model.addAttribute("SCOPE", oAuth2ClientProperty.registration.google.scope.joinToString(" "))
        return "authorization/google/login"
    }

    @GetMapping("/callback")
    fun callback(model: Model): String {
        return "authorization/google/callback"
    }
}
