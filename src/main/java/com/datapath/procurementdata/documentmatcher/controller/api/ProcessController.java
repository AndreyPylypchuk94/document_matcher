package com.datapath.procurementdata.documentmatcher.controller.api;

import com.datapath.procurementdata.documentmatcher.dao.entity.CategoryEntity;
import com.datapath.procurementdata.documentmatcher.dao.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.singletonMap;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("knime")
public class ProcessController {

    @Value("${process.params}")
    private LinkedList<String> PROCESS_PARAMS;

    private static final Map<Integer, Boolean> PROCESS_STATUSES = new HashMap<>();

    private final CategoryRepository categoryRepository;

    @GetMapping("start")
    public ResponseEntity<?> start(@RequestParam Integer categoryId) {
        if (PROCESS_STATUSES.containsKey(categoryId) && PROCESS_STATUSES.get(categoryId))
            return ResponseEntity.status(CONFLICT).build();

        Optional<CategoryEntity> categoryOpt = categoryRepository.findById(categoryId);
        if (categoryOpt.isEmpty() || isEmpty(categoryOpt.get().getWorkflowDirParam()))
            return ResponseEntity.status(NOT_FOUND).build();

        CategoryEntity category = categoryOpt.get();

        PROCESS_STATUSES.put(categoryId, true);
        Thread knimeThread = new Thread(() -> {
            try {
                LinkedList<String> params = new LinkedList<>(PROCESS_PARAMS);
                params.add(category.getWorkflowDirParam());

                ProcessBuilder processBuilder = new ProcessBuilder()
                        .command(params);

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
                log.error("Knime process error", e);
            }
            PROCESS_STATUSES.put(categoryId, false);
        });
        knimeThread.setName("knime-thread-" + categoryId);
        knimeThread.start();
        return ResponseEntity.ok().build();
    }

    @GetMapping("status")
    private ResponseEntity<Map<String, Boolean>> status(@RequestParam Integer categoryId) {
        return ok(singletonMap("isRunning", PROCESS_STATUSES.getOrDefault(categoryId, false)));
    }
}
