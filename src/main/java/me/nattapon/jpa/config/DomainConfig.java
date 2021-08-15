package me.nattapon.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("me.nattapon.jpa.domain")
@EnableJpaRepositories("me.nattapon.jpa.repos")
@EnableTransactionManagement
public class DomainConfig {
}
