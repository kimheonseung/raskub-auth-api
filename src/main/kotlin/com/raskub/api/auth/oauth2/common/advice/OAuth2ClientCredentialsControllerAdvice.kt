package com.raskub.api.auth.oauth2.common.advice

import com.raskub.api.auth.oauth2.common.advice.response.ClientCredentialsErrorResponse
import com.raskub.api.auth.oauth2.common.exception.ClientCredentialsAlreadyExistsException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice("com.raskub.api.auth.oauth2.grant.clientcredentials")
class OAuth2ClientCredentialsControllerAdvice {

    @ExceptionHandler(
        DataIntegrityViolationException::class,
        ClientCredentialsAlreadyExistsException::class
    )
    fun handleBadRequestException(ex: Exception) =
        ResponseEntity<ClientCredentialsErrorResponse>(
            ClientCredentialsErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.message),
            HttpStatus.BAD_REQUEST,
        )

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception) =
        ResponseEntity<ClientCredentialsErrorResponse>(
            ClientCredentialsErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.message),
            HttpStatus.INTERNAL_SERVER_ERROR,
        )
}
