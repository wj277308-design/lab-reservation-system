package com.example.labreservation.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "lab")
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private LabCategory category;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    private String location;

    private Integer capacity;

    private String description;

    // AVAILABLE, MAINTENANCE
    private String status;
}
