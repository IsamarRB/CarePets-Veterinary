package com.CarePets.services;

import com.CarePets.models.Pet;
import com.CarePets.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class PetService {
    @Autowired
    private IPetRepository petRepository;

    public List<Pet> listPet() {
        return (List<Pet>) petRepository.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    public Pet updatePet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet addPet(Pet pet) {return petRepository.save(pet);
    }
}