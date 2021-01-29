package com.course.microservice.api.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Feign client
 */
@FeignClient(name = "target-service-name-on-consul", qualifier = "dummyClient")
public class DummyClient {

}
