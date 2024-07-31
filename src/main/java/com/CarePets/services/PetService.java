package com.CarePets.services;

import com.CarePets.repositories.IPetRepository;
import com.CarePets.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    IPetRepository iIPetRepository;

    public Pet addNewPet(Pet pet) { return iIPetRepository.save(pet);}
}
