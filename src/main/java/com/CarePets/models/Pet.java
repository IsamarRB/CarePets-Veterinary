package com.CarePets.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    public Pet(Long idPet, String name, int age, String race, String gender, String url) {
        this.idPet = idPet;
        this.name = name;
        this.age = age;
        this.race = race;
        this.gender = gender;
        this.url = url;
    }


    public Pet(Pet pet) {
        this.idPet = pet.getIdPet();
        this.name = pet.getName();
        this.age = pet.getAge();
        this.race = pet.getRace();
        this.gender = pet.getGender();
        this.url = pet.getUrl();
    }

    public void update(Pet pet) {
        this.name = pet.getName();
        this.age = pet.getAge();
        this.race = pet.getRace();
        this.gender = pet.getGender();
        this.url = pet.getUrl();
    }

    @OneToMany(mappedBy = "pet")
    @JsonManagedReference("pet")
    private List<Guardian> guardiansList;

    @OneToMany(mappedBy = "pet")
    @JsonManagedReference("pet")
    private List<Appointment> appointmentsList;
}
