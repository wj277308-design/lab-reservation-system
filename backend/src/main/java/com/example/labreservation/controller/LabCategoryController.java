package com.example.labreservation.controller;

import com.example.labreservation.entity.LabCategory;
import com.example.labreservation.service.LabCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab-categories")
@CrossOrigin
public class LabCategoryController {
    @Autowired
    private LabCategoryService labCategoryService;

    @GetMapping
    public List<LabCategory> getAll() {
        return labCategoryService.findAll();
    }

    @PostMapping
    public LabCategory create(@RequestBody LabCategory category) {
        return labCategoryService.save(category);
    }
}
