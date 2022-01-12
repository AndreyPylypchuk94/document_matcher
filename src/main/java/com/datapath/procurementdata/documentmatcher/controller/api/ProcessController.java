package com.datapath.procurementdata.documentmatcher.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("knime")
public class ProcessController {

    @Value("${process.params}")
    private String[] PROCESS_PARAMS;

    private boolean isRun = false;

    @GetMapping("start")
    public ResponseEntity<?> start() {
        if (!isRun) {
            isRun = true;
            Thread knimeThread = new Thread(() -> {
                try {
                    ProcessBuilder processBuilder = new ProcessBuilder()
                            .command(PROCESS_PARAMS);

                    Process process = processBuilder.start();

                    log.info("Knime process started with pid {}", process.pid());

                    InputStreamReader isr = new InputStreamReader(process.getInputStream());
                    BufferedReader br = new BufferedReader(isr);

                    String line;
                    while ((line = br.readLine()) != null)
                        log.info(line);

                    InputStreamReader eisr = new InputStreamReader(process.getErrorStream());
                    BufferedReader ebr = new BufferedReader(eisr);

                    while ((line = ebr.readLine()) != null)
                        log.error(line);

                    log.info("Knime process finished");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isRun = false;
            });
            knimeThread.setName("knime-thread");
            knimeThread.start();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
