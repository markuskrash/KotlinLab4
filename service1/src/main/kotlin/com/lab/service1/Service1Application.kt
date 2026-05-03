package com.lab.service1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class Service1Application

fun main(args: Array<String>) {
    runApplication<Service1Application>(*args)
}
