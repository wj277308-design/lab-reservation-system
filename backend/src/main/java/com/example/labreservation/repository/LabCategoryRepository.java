package com.example.labreservation.repository;

import com.example.labreservation.entity.LabCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabCategoryRepository extends JpaRepository<LabCategory, Long> {
}
