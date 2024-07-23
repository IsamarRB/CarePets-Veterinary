package com.CarePets.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table (name = "Guardian")

public class Guardian {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        @Column(name = "id guardian")
        private Long idGuardian;

        @Column(name = "name and surname")
        private String nameAndSurname;

        @Column(name = "telephone number")
        private int telephoneNumber;

        @ManyToOne
        @JoinColumn (name = "idPet", nullable = false)
        private Pet pet;
    }