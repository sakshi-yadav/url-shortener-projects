package com.sakshi.urlRedirectionService.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("com.**")
@EnableDiscoveryClient
@EnableTransactionManagement
@RefreshScope
public class UrlRedirectionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlRedirectionServiceApplication.class, args);
    }
}