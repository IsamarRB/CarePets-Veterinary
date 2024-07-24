package com.CarePets.controllers;

import com.CarePets.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
