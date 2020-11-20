package net.skideo;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@SpringBootConfiguration
@EnableJpaRepositories(basePackages = {"net.skideo.repository"})
@EntityScan(basePackages = {"net.skideo.model"})
@EnableJpaAuditing(auditorAwareRef = "auditorAwareTest")
public class JpaConfigTest {

    @Bean
    public AuditorAware<String> auditorAwareTest() {
        return () -> Optional.of("Test");
    }

}