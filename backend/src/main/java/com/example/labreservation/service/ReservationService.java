package com.example.labreservation.service;

import com.example.labreservation.entity.Reservation;
import com.example.labreservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public List<Reservation> findByLabId(Long labId) {
        return reservationRepository.findByLabId(labId);
    }

    public Reservation create(Reservation reservation) {
        reservation.setStatus("PENDING");
        return reservationRepository.save(reservation);
    }

    public Reservation updateStatus(Long id, String status) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            reservation.setStatus(status);
            reservation.setAuditTime(new Date());
            return reservationRepository.save(reservation);
        }
        return null;
    }
}
