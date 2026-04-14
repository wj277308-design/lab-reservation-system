package com.example.labreservation.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab lab;

    @Column(name = "reserve_date")
    @Temporal(TemporalType.DATE)
    private Date reserveDate;

    // MORNING, AFTERNOON, EVENING
    @Column(name = "time_slot")
    private String timeSlot;

    private String reason;

    // PENDING, APPROVED, REJECTED, COMPLETED
    private String status;

    @Column(name = "audit_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auditTime;

    @Column(name = "create_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
    }
}
