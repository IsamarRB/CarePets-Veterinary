package com.CarePets.services;

import com.CarePets.models.Guardian;
import com.CarePets.repositories.IGuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuardianService {

    @Autowired
    IGuardianRepository iGuardianRepository;

    public Guardian createGuardian(Guardian guardian) {
        return iGuardianRepository.save(guardian);
    }
}
