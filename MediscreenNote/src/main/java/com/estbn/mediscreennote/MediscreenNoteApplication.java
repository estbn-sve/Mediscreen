package com.estbn.mediscreennote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication()
public class MediscreenNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediscreenNoteApplication.class, args);
    }

}
