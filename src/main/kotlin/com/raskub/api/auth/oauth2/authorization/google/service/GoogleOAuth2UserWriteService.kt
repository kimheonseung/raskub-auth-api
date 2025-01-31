package com.raskub.api.auth.oauth2.authorization.google.service

import com.raskub.api.auth.oauth2.authorization.google.entity.GoogleOAuth2User
import com.raskub.api.auth.oauth2.authorization.google.repository.GoogleOAuth2UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class GoogleOAuth2UserWriteService(
    private val googleOauth2UserRepository: GoogleOAuth2UserRepository,
) {
    fun save(
        googleOauth2User: GoogleOAuth2User
    ) = googleOauth2UserRepository.save(googleOauth2User)
}
