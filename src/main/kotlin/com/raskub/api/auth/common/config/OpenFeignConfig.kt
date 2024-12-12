package com.raskub.api.auth.common.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["com.raskub.api.auth.oauth2"])
class OpenFeignConfig
