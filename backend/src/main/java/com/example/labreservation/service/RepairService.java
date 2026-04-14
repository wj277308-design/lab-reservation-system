package com.example.labreservation.service;

import com.example.labreservation.entity.Repair;
import com.example.labreservation.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {
    @Autowired
    private RepairRepository repairRepository;

    public List<Repair> findAll() {
        return repairRepository.findAll();
    }

    public List<Repair> findByUserId(Long userId) {
        return repairRepository.findByReporterId(userId);
    }

    public List<Repair> findByLabId(Long labId) {
        return repairRepository.findByLabId(labId);
    }

    public Repair create(Repair repair) {
        repair.setStatus("PENDING");
        return repairRepository.save(repair);
    }

    public Repair updateStatus(Long id, String status, String repairUnit) {
        Repair repair = repairRepository.findById(id).orElse(null);
        if (repair != null) {
            repair.setStatus(status);
            if (repairUnit != null) {
                repair.setRepairUnit(repairUnit);
            }
            return repairRepository.save(repair);
        }
        return null;
    }
}
