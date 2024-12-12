package com.raskub.api.auth.credentials.filter

import com.raskub.api.auth.credentials.exception.CredentialsException
import com.raskub.api.auth.credentials.repository.CredentialsRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.Base64

class CredentialsFilter(
    private val credentialsRepository: CredentialsRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
) : Filter {
    private val log = KotlinLogging.logger { }
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

        try {
            val authHeader = httpRequest.getHeader("Authorization")
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw CredentialsException("Missing or invalid Authorization header")
            }
            log.info { "Header: $authHeader" }
            val token = authHeader.substring(7)
            val decodedToken = String(Base64.getDecoder().decode(token))
            val usernameAndPassword = decodedToken.split(":")
            if (usernameAndPassword.size != 2) {
                throw CredentialsException("Invalid Authorization header")
            }
            log.info { "usernameAndPassword: $usernameAndPassword" }
            val username = usernameAndPassword[0]
            val password = usernameAndPassword[1]
            val bcryptSecret = credentialsRepository.findByClientId(username)?.clientSecret
            if (!bCryptPasswordEncoder.matches(password, bcryptSecret)) {
                throw CredentialsException("Invalid Authorization header")
            }
        } catch (e: CredentialsException) {
            httpResponse.status = HttpServletResponse.SC_UNAUTHORIZED
            httpResponse.writer.write(e.message)
            return
        } catch (e: Exception) {
            httpResponse.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            httpResponse.writer.write("Something went wrong")
            return
        }

        log.info { "Success to authenticate" }
        chain?.doFilter(request, response)
    }
}
