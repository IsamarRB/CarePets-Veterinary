package com.CarePets.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table (name = "Appointment")

public class Appointment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        @Column(name = "id appointment")
        private Long idAppointment;

        @Column(name = "date and time")
        private LocalDateTime dateTime;

        @Column(name = "patient")
        private String patient;

        @Column(name = "standard/urgent consultation")
        private String typeConsult;

        @Column(name = "reason of the appointment")
        private String reason;

        @Column(name = "appointment status")
        private String status;
    }

