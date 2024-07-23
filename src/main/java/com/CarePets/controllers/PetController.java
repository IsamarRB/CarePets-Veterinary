package com.CarePets.controllers;

import com.CarePets.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@CrossOrigin(origins = "*")
public class PetController {
    @Autowired
     PetService petService;

    @DeleteMapping(path = "images/{id}")
    public void deletePet(@PathVariable Long id){
        petService.deletePet(id);
    }
}
