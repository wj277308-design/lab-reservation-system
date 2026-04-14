package com.example.labreservation.service;

import com.example.labreservation.entity.Lab;
import com.example.labreservation.repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabService {

    @Autowired
    private LabRepository labRepository;

    public List<Lab> findAll() {
        return labRepository.findAll();
    }

    public List<Lab> search(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return labRepository.findAll();
        }
        return labRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Lab findById(Long id) {
        return labRepository.findById(id).orElse(null);
    }

    public Lab save(Lab lab) {
        return labRepository.save(lab);
    }

    public void deleteById(Long id) {
        labRepository.deleteById(id);
    }
}
