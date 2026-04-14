package com.example.labreservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin // Allow cross-origin for Vue
public class TestController {

    @GetMapping("/api/test")
    public String test() {
        return "Backend is running!";
    }
}
