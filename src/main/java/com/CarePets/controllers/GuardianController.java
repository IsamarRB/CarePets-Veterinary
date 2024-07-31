package com.CarePets.controllers;

import com.CarePets.models.Guardian;
import com.CarePets.services.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

import static org.apache.tomcat.util.http.parser.MediaType.*;

@RestController
@RequestMapping("/guardian")
@CrossOrigin(origins = "*")

public class GuardianController {
@Autowired
    GuardianService guardianService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guardian> getByGuardiansById(@PathVariable Long id) {
        Optional<Guardian> guardian = guardianService.getByGuardiansById(id);
        if (guardian.isPresent()) {
            return ResponseEntity.ok(guardian.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Guardian> getAllGuardians() {
        return guardianService.getAllGuardians();
    }

}


