package com.jt;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class Starter {

    @Resource
    private TransportClient t;

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}
