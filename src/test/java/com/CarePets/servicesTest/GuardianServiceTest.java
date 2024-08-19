package com.CarePets.servicesTest;

import com.CarePets.models.Guardian;
import com.CarePets.repositories.IGuardianRepository;
import com.CarePets.services.GuardianService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import com.CarePets.models.Pet;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import java.util.ArrayList;


class GuardianServiceTest {
    @Mock
    private IGuardianRepository iGuardianRepository;

    @InjectMocks
    private GuardianService guardianService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreateGuardian() {

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

    @Test
    void test_if_updateGuardian_updates_information() {
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

    @Test
    void deleteGuardian_shouldDeleteGuardian() {
        Long id = 1L;
        guardianService.deleteGuardian(id);
        verify(iGuardianRepository, times(1)).deleteById(id);
    }

    public GuardianServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetByGuardiansById() {
        Guardian guardian = new Guardian(1L, "John Doe", 123456789, null);
        when(iGuardianRepository.findById(1L)).thenReturn(Optional.of(guardian));

        Optional<Guardian> result = guardianService.getByGuardiansById(1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getNameAndSurname());
    }

    @Test
    public void testGetAllGuardians() {
        ArrayList<Guardian> guardians = new ArrayList<>();
        guardians.add(new Guardian(1L, "John Doe", 123456789, null));
        when(iGuardianRepository.findAll()).thenReturn(guardians);

        ArrayList<Guardian> result = guardianService.getAllGuardians();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getNameAndSurname());
    }
}
