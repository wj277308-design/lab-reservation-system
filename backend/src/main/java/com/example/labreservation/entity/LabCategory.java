package com.example.labreservation.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "lab_category")
public class LabCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
}
