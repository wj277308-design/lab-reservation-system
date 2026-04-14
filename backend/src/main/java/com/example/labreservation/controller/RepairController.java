package com.example.labreservation.controller;

import com.example.labreservation.entity.Repair;
import com.example.labreservation.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/repairs")
@CrossOrigin
public class RepairController {
    @Autowired
    private RepairService repairService;

    @GetMapping
    public List<Repair> getAll() {
        return repairService.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Repair> getByUserId(@PathVariable Long userId) {
        return repairService.findByUserId(userId);
    }

    @GetMapping("/lab/{labId}")
    public List<Repair> getByLabId(@PathVariable Long labId) {
        return repairService.findByLabId(labId);
    }

    @PostMapping
    public Repair create(@RequestBody Repair repair) {
        return repairService.create(repair);
    }

    @PutMapping("/{id}/status")
    public Repair updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return repairService.updateStatus(id, body.get("status"), body.get("repairUnit"));
    }
}
