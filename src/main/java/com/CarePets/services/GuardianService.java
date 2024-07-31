package com.CarePets.services;

import com.CarePets.models.Guardian;
import com.CarePets.repositories.IGuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GuardianService {
    @Autowired
    IGuardianRepository iGuardianRepository;

    public Optional<Guardian> getByGuardiansById(Long id) { return iGuardianRepository.findById(id); }

    public ArrayList<Guardian> getAllGuardians() { return (ArrayList<Guardian>) iGuardianRepository.findAll(); }
}
