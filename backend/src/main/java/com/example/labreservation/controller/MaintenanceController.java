package com.example.labreservation.controller;

import com.example.labreservation.entity.Maintenance;
import com.example.labreservation.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@CrossOrigin
public class MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping
    public List<Maintenance> getAll() {
        return maintenanceService.findAll();
    }

    @GetMapping("/lab/{labId}")
    public List<Maintenance> getByLabId(@PathVariable Long labId) {
        return maintenanceService.findByLabId(labId);
    }

    @PostMapping
    public Maintenance create(@RequestBody Maintenance maintenance) {
        return maintenanceService.create(maintenance);
    }
}
