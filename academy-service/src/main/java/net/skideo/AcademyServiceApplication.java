package net.skideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ImportAutoConfiguration(AuthSerivceApplication.class)
public class AcademyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademyServiceApplication.class, args);
    }

}
