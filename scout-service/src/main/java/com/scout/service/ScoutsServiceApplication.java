package com.scout.service;

import data.service.DataApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(DataApplication.class)
public class ScoutsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScoutsServiceApplication.class, args);
    }

}
