package com.CarePets.services;

import com.CarePets.models.Pet;
import com.CarePets.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class PetService {

    @Autowired
    IPetRepository iPetRepository;


    public void deletePet(Long id){
        iPetRepository.deleteById(id);
    }

    public List<Pet> listPet() {
        return (List<Pet>)iPetRepository.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return iPetRepository.findById(id);
    }

    public Pet updatePet(Pet pet) {
        return iPetRepository.save(pet);
    }
    public Pet addPet(Pet pet) {return iPetRepository.save(pet);
    }
    public Pet addNewPet(Pet pet) { return iIPetRepository.save(pet);}

}