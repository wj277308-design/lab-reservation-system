package com.example.labreservation;

import com.example.labreservation.entity.User;
import com.example.labreservation.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LabReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabReservationApplication.class, args);
    }
}
