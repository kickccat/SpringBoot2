package io.sample.springbootapi.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogTestAPI {
    
    @GetMapping("/log")
    public String log() {
        log.info("Info ---------- log");
        log.warn("Warn ---------- log");
        log.error("Error ---------- log");
        log.debug("Debug ---------- log");
        log.trace("Trace ---------- log");
        return "logTest";
    }
}
