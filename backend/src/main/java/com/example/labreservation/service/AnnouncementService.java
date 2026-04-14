package com.example.labreservation.service;

import com.example.labreservation.entity.Announcement;
import com.example.labreservation.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;

    public List<Announcement> findAll() {
        return announcementRepository.findAll();
    }

    public Announcement save(Announcement announcement) {
        return announcementRepository.save(announcement);
    }
}
