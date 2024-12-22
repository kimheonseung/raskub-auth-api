package com.raskub.api.auth.apikey.filter

import com.raskub.api.auth.apikey.constant.Services
import com.raskub.api.auth.apikey.exception.ApiKeyException
import com.raskub.api.auth.apikey.exception.UnknownServiceException
import com.raskub.api.auth.apikey.properties.ApiKeyProperties
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class ApiKeyFilter(
    private val apiKeyProperties: ApiKeyProperties,
) : Filter {

    private val log = KotlinLogging.logger { }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

        try {
            val clientName = httpRequest.getHeader(apiKeyProperties.header.clientName)
            val clientId = httpRequest.getHeader(apiKeyProperties.header.clientId)
            val clientSecret = httpRequest.getHeader(apiKeyProperties.header.clientSecret)
            if (clientName == null || clientId == null || clientSecret == null) {
                throw ApiKeyException("Missing required headers")
            }

            val service: Services = Services.byClientName(clientName)

            when (service) {
                Services.COMMON_API -> {
                    if (clientId != apiKeyProperties.services.commonApi.clientId ||
                        clientSecret != apiKeyProperties.services.commonApi.clientSecret
                    ) {
                        throw ApiKeyException("Invalid API Key")
                    }
                }
            }
        } catch (e: ApiKeyException) {
            httpResponse.status = HttpServletResponse.SC_UNAUTHORIZED
            httpResponse.writer.write(e.message)
            return
        } catch (e: UnknownServiceException) {
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
