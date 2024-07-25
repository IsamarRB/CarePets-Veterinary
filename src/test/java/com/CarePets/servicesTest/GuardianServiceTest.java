package com.CarePets.servicesTest;

import com.CarePets.models.Guardian;
import com.CarePets.repositories.IGuardianRepository;
import com.CarePets.services.GuardianService;
import com.CarePets.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GuardianServiceTest {
    @Mock
    IGuardianRepository iGuardianRepository;

    @InjectMocks
    GuardianService guardianService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateGuardian(){

        Guardian guardianToSave = new Guardian();
        Long id = 1L;
        String nameAndSurname = "Juan Molina";
        int telephoneNumber = 683_340_343;

        guardianToSave.setIdGuardian(id);
        guardianToSave.setNameAndSurname(nameAndSurname);
        guardianToSave.setTelephoneNumber(telephoneNumber);

        when(iGuardianRepository.save(guardianToSave)).thenReturn(guardianToSave);

        Guardian result = guardianService.createGuardian(guardianToSave);

        assertEquals(1L, result.getIdGuardian());
        assertEquals("Juan Molina", result.getNameAndSurname());
        assertEquals(683_340_343, result.getTelephoneNumber());

        verify(iGuardianRepository, times(1)).save(guardianToSave);
    }
}
