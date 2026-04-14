package com.example.labreservation.controller;

import com.example.labreservation.entity.Announcement;
import com.example.labreservation.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@CrossOrigin
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping
    public List<Announcement> getAll() {
        return announcementService.findAll();
    }

    @PostMapping
    public Announcement create(@RequestBody Announcement announcement) {
        return announcementService.save(announcement);
    }
}
