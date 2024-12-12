package com.raskub.api.auth.common.extension

import java.util.Base64

fun String.toDecodeBase64(): String = String(Base64.getDecoder().decode(this))
