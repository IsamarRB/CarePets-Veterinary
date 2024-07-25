package com.CarePets.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

        @Column(name = "standard/urgent consultation")
        private String typeConsult;

        @Column(name = "reason of the appointment")
        private String reason;

        @Column(name = "appointment status")
        private String status;

        @ManyToOne
        @JoinColumn (name = "idPet", nullable = false)
        @JsonManagedReference
        private Pet pet;


    }

