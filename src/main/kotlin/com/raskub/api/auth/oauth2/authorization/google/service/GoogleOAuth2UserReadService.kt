package com.raskub.api.auth.oauth2.authorization.google.service

import com.raskub.api.auth.oauth2.authorization.google.entity.GoogleOAuth2User
import com.raskub.api.auth.oauth2.authorization.google.repository.GoogleOAuth2UserRepository
import org.springframework.stereotype.Service

@Service
class GoogleOAuth2UserReadService(
    private val googleOauth2UserRepository: GoogleOAuth2UserRepository,
) {
    fun findBySubOrNull(
        sub: String
    ): GoogleOAuth2User? = googleOauth2UserRepository.findBySub(sub)
}
