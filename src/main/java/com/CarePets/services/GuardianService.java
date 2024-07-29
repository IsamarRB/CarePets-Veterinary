package com.CarePets.services;

import com.CarePets.repositories.IGuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuardianService {

    @Autowired
    private IGuardianRepository guardianRepository;


    public void deleteGuardian(Long id) {
        guardianRepository.deleteById(id);
    }
}


