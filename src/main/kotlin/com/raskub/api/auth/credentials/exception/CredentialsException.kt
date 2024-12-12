package com.raskub.api.auth.credentials.exception

class CredentialsException(
    override val message: String,
) : RuntimeException(message)
