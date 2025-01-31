package com.raskub.api.auth.oauth2.authorization.google.parser

import com.nimbusds.jwt.JWTParser
import com.raskub.api.auth.oauth2.authorization.google.model.GoogleIdToken
import org.springframework.stereotype.Component
import java.util.Date

@Component
class GoogleIdTokenParser {
    fun parseGoogleIdToken(
        idToken: String
    ): GoogleIdToken {
        val claimsMap = JWTParser.parse(idToken).jwtClaimsSet.claims
        val sub = claimsMap["sub"] as String
        val email = claimsMap["email"] as String
        val name = claimsMap["name"] as String?
        val pictureUrl = claimsMap["picture"] as String?
        val iat = claimsMap["iat"] as Date
        val exp = claimsMap["exp"] as Date
        return GoogleIdToken(
            sub = sub,
            email = email,
            name = name,
            pictureUrl = pictureUrl,
            iatMillis = iat.time,
            expMillis = exp.time,
        )
    }
}
