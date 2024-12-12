package com.raskub.api.auth.apikey.exception

class ApiKeyException(
    override val message: String,
) : RuntimeException(message)
