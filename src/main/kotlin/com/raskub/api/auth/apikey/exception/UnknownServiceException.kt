package com.raskub.api.auth.apikey.exception

class UnknownServiceException(
    override val message: String,
) : RuntimeException(message)
