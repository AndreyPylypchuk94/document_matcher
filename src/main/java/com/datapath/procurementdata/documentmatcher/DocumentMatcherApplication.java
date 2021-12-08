package com.datapath.procurementdata.documentmatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
public class DocumentMatcherApplication {

    public static void main(String[] args) {
        SpringApplication application =
                new SpringApplication(DocumentMatcherApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }
}
