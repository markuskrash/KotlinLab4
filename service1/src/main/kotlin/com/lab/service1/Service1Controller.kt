package com.lab.service1

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.time.Instant

@RestController
@RequestMapping("/api")
class Service1Controller(
    private val restTemplate: RestTemplate,
) {

    @GetMapping("/service1")
    fun service1(): Map<String, Any> {
        @Suppress("UNCHECKED_CAST")
        val fromService2 = restTemplate.getForObject(
            "http://service2/api/service2",
            Map::class.java,
        ) as Map<String, Any>

        return mapOf(
            "from" to "service1",
            "message" to "Агрегированный ответ: service1 + service2",
            "local" to mapOf(
                "timestamp" to Instant.now().toString(),
            ),
            "service2" to fromService2,
        )
    }
}
