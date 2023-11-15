package com.exalt.adapter;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.exalt.adapter")
@EnableJpaRepositories("com.exalt.adapter")
//@SpringBootApplication(scanBasePackages = {"com.exalt.adapter", "com.exalt.adapter.repository"})
public class JpaConfig {
}
