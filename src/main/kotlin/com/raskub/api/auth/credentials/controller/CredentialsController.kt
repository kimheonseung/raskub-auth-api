package com.raskub.api.auth.credentials.controller

import com.raskub.api.auth.common.constant.URIConstant
import com.raskub.api.auth.common.response.ApiResponse
import com.raskub.api.auth.credentials.service.CredentialsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(URIConstant.CREDENTIALS_BASE_URI_V1)
class CredentialsController(
    private val credentialsService: CredentialsService,
) {
    @PostMapping
    fun getByClientName(
        @RequestBody request: CreateClientCredentialsRequest,
    ) = ApiResponse(data = credentialsService.createClientCredentialsByClientName(request.clientName))

    @PostMapping("/verify")
    fun postVerify() = ApiResponse(data = true)
}
