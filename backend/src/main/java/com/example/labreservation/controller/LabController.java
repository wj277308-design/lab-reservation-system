package com.example.labreservation.controller;

import com.example.labreservation.entity.Lab;
import com.example.labreservation.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labs")
@CrossOrigin
public class LabController {

    @Autowired
    private LabService labService;

    @GetMapping
    public List<Lab> getAllLabs(@RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return labService.search(keyword);
        }
        return labService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lab> getLabById(@PathVariable Long id) {
        Lab lab = labService.findById(id);
        return lab != null ? ResponseEntity.ok(lab) : ResponseEntity.notFound().build();
    }

    // Only Admin should access this in real app
    @PostMapping
    public Lab createLab(@RequestBody Lab lab) {
        return labService.save(lab);
    }
}
