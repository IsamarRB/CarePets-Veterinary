package com.CarePets.controllers;

import com.CarePets.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.CarePets.exceptions.PetNotFoundException;
import com.CarePets.models.Pet;
import com.CarePets.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/pet")
@CrossOrigin(origins = "*")

public class PetController {
    @Autowired
     PetService petService;

    @DeleteMapping(path = "pets/{id}")
    public void deletePet(@PathVariable Long id){
        petService.deletePet(id);
    }
    @GetMapping
    public List<Pet> listPet() {
        return petService.listPet().stream()
                .map(Pet::new)
                .collect(Collectors.toList());
        }
    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet petDetails) {
        Pet existingPet = petService.getPetById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + id));

        existingPet.setName(petDetails.getName());
        existingPet.setAge(petDetails.getAge());
        existingPet.setRace(petDetails.getRace());
        existingPet.setGender(petDetails.getGender());
        existingPet.setUrl(petDetails.getUrl());

        return petService.updatePet(existingPet);
    }
}
