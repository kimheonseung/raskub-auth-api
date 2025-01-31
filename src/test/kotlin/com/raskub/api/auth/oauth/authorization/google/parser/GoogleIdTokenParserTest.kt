package com.raskub.api.auth.oauth.authorization.google.parser

import com.raskub.api.auth.oauth2.authorization.google.parser.GoogleIdTokenParser
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe

class GoogleIdTokenParserTest : DescribeSpec({
    describe("Google IdToken Parser 테스트") {
        val googleIdTokenParser = GoogleIdTokenParser()
        context("GoogleIdTokenParser가 주어질 때") {
            val idToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImZhMDcyZjc1Nzg0NjQyNjE1MDg3YzcxODJjMTAxMzQxZTE4ZjdhM2EiLCJ" +
                "0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI3MTkwMTkyMzc3Mi1iYzUwaz" +
                "M1M2hrMGxsM2Q3NDl1bW01YnZoN2Y5NzNlNS5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImF1ZCI6IjcxOTAxOTIzNzcyL" +
                "WJjNTBrMzUzaGswbGwzZDc0OXVtbTVidmg3Zjk3M2U1LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTE1NDU1M" +
                "jI5ODcwOTgzOTQ2MjE0IiwiZW1haWwiOiJraHM5MjAyMTBAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF0X2hh" +
                "c2giOiIzc2FidW1DXzNIWHZLNVN4X1dtaVFBIiwibmFtZSI6Ikhlb25TZXVuZyBLaW0iLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDM" +
                "uZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUNnOG9jS1Uxc0xITEpnWkNlQWFqSTNNSGN6dXpzUThPOEh5RUkxUXR5ZDhpaWVFW" +
                "k9PQ3U3R0VwQT1zOTYtYyIsImdpdmVuX25hbWUiOiJIZW9uU2V1bmciLCJmYW1pbHlfbmFtZSI6IktpbSIsImlhdCI6MTczODIz" +
                "Mzg2MSwiZXhwIjoxNzM4MjM3NDYxfQ.nUWrNjsR3CqWL8N97GDDPrEp2KjYvBSqypGANiFv0I11gcfl7zAE1hM_J_ICWbeKAlVoi" +
                "ONCsYK2maS7VOvtJUgLxaf8B6Odu2NXjHr8P4nHOujts_y6XjfT5X9Uo70-y-TEfiDkmRzItDJBb_XSemvgKlxejWZX4sjKsLKu" +
                "x0lvN9UNJi1Y5gyHgtoRtDNWYnTZNgBq3E5vlTw5WblwNne7hTrZttK6qvUIyhuExZiGF8GYDlZHeAAuLCsoou4Tkb9FUXU7t4cs" +
                "t096V-VwanqxS_6zQOn5oLnmcdxuAaupkVVI2Hg1JaY0Z7cygwirt6kXt-OhZ_v2VxPDq-9hQA"
            it("ID Token으로부터 GoogleOAuth2User를 파싱한다.") {
                val googleIdToken = googleIdTokenParser.parseGoogleIdToken(idToken)
                googleIdToken shouldNotBe null
            }
        }
    }
})
