package com.CarePets.controllers;

import com.CarePets.models.Guardian;
import com.CarePets.repositories.IGuardianRepository;
import com.CarePets.services.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
@CrossOrigin(origins = "*")
public class GuardianController {
    @Autowired
    GuardianService guardianService;

    @PostMapping(path = "/pets")
    public Guardian createGuardian(@RequestBody Guardian guardian) {
        return guardianService.createGuardian(guardian);
    }
}
