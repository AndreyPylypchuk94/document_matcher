package com.datapath.procurementdata.documentmatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DocumentMatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentMatcherApplication.class, args);
    }

    @PostConstruct
    private void initDB() {

    }
}
