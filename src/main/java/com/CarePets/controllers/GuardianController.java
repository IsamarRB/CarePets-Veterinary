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

    @PostMapping(path = "/guardians")
    public Guardian createGuardian(@RequestBody Guardian guardian) {
        return guardianService.createGuardian(guardian);
    }
    @PutMapping(path = "/guardians/{id}")
    public Guardian updateGuardian(@RequestBody Guardian guardian, @PathVariable Long id) {
        return guardianService.updateGuardian(id, guardian);
    }
    @DeleteMapping("/{id}")
    public void deleteGuardian(@PathVariable Long id) {
        guardianService.deleteGuardian(id);
    }

        }