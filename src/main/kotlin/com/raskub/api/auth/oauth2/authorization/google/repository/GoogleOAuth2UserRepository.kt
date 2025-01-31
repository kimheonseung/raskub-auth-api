package com.raskub.api.auth.oauth2.authorization.google.repository

import com.raskub.api.auth.oauth2.authorization.google.entity.GoogleOAuth2User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GoogleOAuth2UserRepository : JpaRepository<GoogleOAuth2User, Long> {
    fun findBySub(sub: String): GoogleOAuth2User?
}
