package com.example.labreservation.repository;

import com.example.labreservation.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
    List<Repair> findByReporterId(Long reporterId);

    List<Repair> findByLabId(Long labId);
}
