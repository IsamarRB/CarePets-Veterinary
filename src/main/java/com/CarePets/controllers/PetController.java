package com.CarePets.controllers;

import com.CarePets.models.Pet;
import com.CarePets.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pet")
@CrossOrigin(origins = "*")

public class PetController {
@Autowired
PetService petService;

    @PostMapping(path = "/pets")
    public Pet addNewPet(@RequestBody Pet pet){ return petService.addNewPet(pet);    }
}
