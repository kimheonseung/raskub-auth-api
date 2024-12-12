package com.raskub.api.auth.common.config

import io.fabric8.kubernetes.api.model.Secret
import io.fabric8.kubernetes.client.KubernetesClientBuilder
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class KubernetesSecretPrinter {
    @PostConstruct
    fun printSecrets() {
        KubernetesClientBuilder().build().use { client ->
            val secret: Secret? = client.secrets().inNamespace("raskub-app")
                .list().items.firstOrNull { it.metadata.name == "raskub-auth-api-secret" }
            if (secret != null) {
                println("Name: ${secret.metadata.name}")
                println("Namespace: ${secret.metadata.namespace}")
                println("Data:")
                secret.data?.forEach { (key, value) ->
                    println("  $key: ${value?.substring(0, 5)}")
                }
            }
        }
    }
}
