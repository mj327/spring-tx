package com.agiview.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class TransactionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionDemoApplication.class, args);
    }

}
