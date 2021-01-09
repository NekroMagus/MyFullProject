package net.skideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SkideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkideoApplication.class, args);
    }

}

