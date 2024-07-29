package com.CarePets.controllers;

import com.CarePets.models.Guardian;
import com.CarePets.services.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guardian")
@CrossOrigin(origins = "*")
public class GuardianController {
    @Autowired
    GuardianService guardianService;

    @PutMapping(path = "/guardians/{id}")
    public Guardian updateGuardian(@RequestBody Guardian guardian, @PathVariable Long id) {
        return guardianService.updateGuardian(id, guardian);
    }
}
