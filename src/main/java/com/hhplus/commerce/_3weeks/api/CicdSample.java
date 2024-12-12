package com.hhplus.commerce._3weeks.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CicdSample {

    private final String version = "v1.0";

    @GetMapping("/")
    public String index() {
        return "hi, gwangho current version : " + version;
    }
}
