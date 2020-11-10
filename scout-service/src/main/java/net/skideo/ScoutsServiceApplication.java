package net.skideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class ScoutsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScoutsServiceApplication.class, args);
    }

}
