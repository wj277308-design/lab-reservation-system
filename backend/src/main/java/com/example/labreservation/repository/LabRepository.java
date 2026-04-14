package com.example.labreservation.repository;

import com.example.labreservation.entity.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRepository extends JpaRepository<Lab, Long> {
    List<Lab> findByNameContainingIgnoreCase(String name);
}
