package com.example.labreservation.service;

import com.example.labreservation.entity.Maintenance;
import com.example.labreservation.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;

    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    public List<Maintenance> findByLabId(Long labId) {
        return maintenanceRepository.findByLabId(labId);
    }

    public Maintenance create(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }
}
