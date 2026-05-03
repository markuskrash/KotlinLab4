package com.lab.service1

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class Service1Config {

    @Bean
    @LoadBalanced
    fun loadBalancedRestTemplate(): RestTemplate = RestTemplate()
}
