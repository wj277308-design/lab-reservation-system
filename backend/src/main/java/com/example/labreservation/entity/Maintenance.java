package com.example.labreservation.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "maintenance")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab lab;

    private String maintainer;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "maintenance_date")
    @Temporal(TemporalType.DATE)
    private Date maintenanceDate;

    @Column(name = "create_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
    }
}
