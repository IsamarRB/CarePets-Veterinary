package com.CarePets.services;

import com.CarePets.models.Guardian;
import com.CarePets.repositories.IGuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuardianService {

    @Autowired
    IGuardianRepository iGuardianRepository;

    public Guardian createGuardian(Guardian guardian) {
        return iGuardianRepository.save(guardian);
    }

    @Autowired
    private IGuardianRepository guardianRepository;
    public Guardian updateGuardian(Long id, Guardian newGuardian) {
        Optional<Guardian> optionalOldGuardian = iGuardianRepository.findById(id);

        Guardian oldGuardian = optionalOldGuardian.get();
        oldGuardian.setNameAndSurname(newGuardian.getNameAndSurname());
        oldGuardian.setTelephoneNumber(newGuardian.getTelephoneNumber());

        return iGuardianRepository.save(oldGuardian);
    }

    public void deleteGuardian(Long id) {
        iGuardianRepository.deleteById(id);
    }
}
