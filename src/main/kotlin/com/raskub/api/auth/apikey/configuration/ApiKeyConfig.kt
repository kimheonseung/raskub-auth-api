package com.raskub.api.auth.apikey.configuration

import com.raskub.api.auth.apikey.filter.ApiKeyFilter
import com.raskub.api.auth.apikey.properties.ApiKeyProperties
import com.raskub.api.auth.common.constant.URIConstant
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(ApiKeyProperties::class)
class ApiKeyConfig(
    private val apiKeyProperties: ApiKeyProperties,
) {
    @Bean(name = ["apiKeyFilterRegistration"])
    fun apiKeyFilterRegistration(): FilterRegistrationBean<ApiKeyFilter> {
        val registrationBean = FilterRegistrationBean<ApiKeyFilter>()
        registrationBean.filter = apiKeyFilter()
        registrationBean.addUrlPatterns(URIConstant.API_KEY_BASE_URI_V1) // 필터를 적용할 경로
        registrationBean.order = 1 // 필터 체인 내 순서 설정
        return registrationBean
    }

    @Bean
    fun apiKeyFilter(): ApiKeyFilter = ApiKeyFilter(apiKeyProperties)
}
