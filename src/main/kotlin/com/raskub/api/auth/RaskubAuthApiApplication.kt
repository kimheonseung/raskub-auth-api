package com.raskub.api.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RaskubAuthApiApplication

fun main(args: Array<String>) {
    runApplication<RaskubAuthApiApplication>(*args)
}
