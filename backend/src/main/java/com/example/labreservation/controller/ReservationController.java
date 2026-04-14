package com.example.labreservation.controller;

import com.example.labreservation.entity.Reservation;
import com.example.labreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Reservation> getByUserId(@PathVariable Long userId) {
        return reservationService.findByUserId(userId);
    }

    @GetMapping("/lab/{labId}")
    public List<Reservation> getByLabId(@PathVariable Long labId) {
        return reservationService.findByLabId(labId);
    }

    @PostMapping
    public Reservation create(@RequestBody Reservation reservation) {
        return reservationService.create(reservation);
    }

    @PutMapping("/{id}/status")
    public Reservation updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return reservationService.updateStatus(id, body.get("status"));
    }
}
