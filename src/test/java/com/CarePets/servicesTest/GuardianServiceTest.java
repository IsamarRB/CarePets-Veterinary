package com.CarePets.servicesTest;

import com.CarePets.models.Guardian;
import com.CarePets.models.Pet;
import com.CarePets.repositories.IGuardianRepository;
import com.CarePets.services.GuardianService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.Guard;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GuardianServiceTest {

    @Mock
    private IGuardianRepository iGuardianRepository;

    @InjectMocks
    private GuardianService guardianService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_if_updateGuardian_updates_information(){
        Long id = 1L;
        Pet bolita = new Pet();
        Guardian oldGuardian = new Guardian(id, "Joselitro García", 123456789, bolita);
        Guardian newGuardian = new Guardian(id, "Kid A", 987654321, bolita);

        when(iGuardianRepository.findById(id)).thenReturn(Optional.of(oldGuardian));
        when(iGuardianRepository.save(any(Guardian.class))).thenReturn(oldGuardian);

        // Act
        Guardian updatedGuardian = guardianService.updateGuardian(id, newGuardian);

        // Assert
        assertEquals(newGuardian.getNameAndSurname(), updatedGuardian.getNameAndSurname());
        assertEquals(newGuardian.getTelephoneNumber(), updatedGuardian.getTelephoneNumber());

       }
}