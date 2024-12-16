package com.raskub.api.auth.credentials.config

import com.raskub.api.auth.common.constant.URIConstant
import com.raskub.api.auth.credentials.filter.CredentialsFilter
import com.raskub.api.auth.credentials.repository.CredentialsRepository
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class CredentialsSecurityConfig(
    private val credentialsRepository: CredentialsRepository,
) {
    // @Bean(name = ["credentialsVerifySecurityFilterChain"])
    // fun credentialsVerifySecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
    //     return http
    //         .cors { }
    //         .csrf { it.disable() }
    //         .securityMatcher("${URIConstant.CREDENTIALS_BASE_URI_V1}/verify")
    //         .authorizeHttpRequests {
    //             it.requestMatchers("${URIConstant.CREDENTIALS_BASE_URI_V1}/verify").permitAll()
    //         }
    //         .addFilterBefore(credentialsFilter(), SecurityContextHolderFilter::class.java)
    //         .build()
    // }

    @Bean
    fun customFilterRegistration(): FilterRegistrationBean<CredentialsFilter> {
        val registrationBean = FilterRegistrationBean<CredentialsFilter>()
        registrationBean.filter = credentialsFilter()
        registrationBean.addUrlPatterns("${URIConstant.CREDENTIALS_BASE_URI_V1}/verify") // 필터를 적용할 경로
        registrationBean.order = 1 // 필터 체인 내 순서 설정
        return registrationBean
    }

    @Bean
    fun credentialsFilter(): CredentialsFilter = CredentialsFilter(credentialsRepository, BCryptPasswordEncoder())
}
