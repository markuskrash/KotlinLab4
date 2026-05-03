package com.lab.service2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class Service2Application

fun main(args: Array<String>) {
    runApplication<Service2Application>(*args)
}
