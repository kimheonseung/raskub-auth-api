package com.raskub.api.auth.apikey.controller

import com.raskub.api.auth.common.constant.URIConstant
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(URIConstant.API_KEY_BASE_URI_V1)
class ApiKeyController {
    @GetMapping
    fun get(): Boolean = true
}
