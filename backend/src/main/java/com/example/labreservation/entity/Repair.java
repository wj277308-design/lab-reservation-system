package com.example.labreservation.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "repair")
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab lab;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "repair_unit")
    private String repairUnit;

    // PENDING, ASSIGNED, COMPLETED
    private String status;

    @Column(name = "create_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
    }
}
