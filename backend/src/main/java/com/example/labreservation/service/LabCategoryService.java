package com.example.labreservation.service;

import com.example.labreservation.entity.LabCategory;
import com.example.labreservation.repository.LabCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabCategoryService {
    @Autowired
    private LabCategoryRepository labCategoryRepository;

    public List<LabCategory> findAll() {
        return labCategoryRepository.findAll();
    }

    public LabCategory save(LabCategory category) {
        return labCategoryRepository.save(category);
    }
}
