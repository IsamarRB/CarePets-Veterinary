package com.CarePets.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table (name = "Pet")

public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id pet")
    private Long idPet;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "race")
    private String race;

    @Column(name = "gender")
    private String gender;

    @Column(name = "url")
    private String url;
    @JsonIgnoreProperties("pet")
    @OneToMany(mappedBy = "pet")
    private List<Guardian> guardians;
    @OneToMany(mappedBy = "pet")
    private List<Appointment> appointmentList;
}
