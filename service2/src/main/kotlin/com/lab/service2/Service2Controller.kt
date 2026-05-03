package com.lab.service2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/api")
class Service2Controller {

    @GetMapping("/service2")
    fun service2(): Map<String, Any> = mapOf(
        "from" to "service2",
        "message" to "Данные из Service 2",
        "timestamp" to Instant.now().toString(),
    )
}
