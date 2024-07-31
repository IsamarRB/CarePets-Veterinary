package com.CarePets.servicesTest;

import com.CarePets.models.Guardian;
import com.CarePets.repositories.IGuardianRepository;
import com.CarePets.services.GuardianService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GuardianServiceTest {
    @Mock
    private IGuardianRepository guardianRepository;

    @InjectMocks
    private GuardianService guardianService;

    public GuardianServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetByGuardiansById() {
        Guardian guardian = new Guardian(1L, "John Doe", 123456789, null);
        when(guardianRepository.findById(1L)).thenReturn(Optional.of(guardian));

        Optional<Guardian> result = guardianService.getByGuardiansById(1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getNameAndSurname());
    }

    @Test
    public void testGetAllGuardians() {
        ArrayList<Guardian> guardians = new ArrayList<>();
        guardians.add(new Guardian(1L, "John Doe", 123456789, null));
        when(guardianRepository.findAll()).thenReturn(guardians);

        ArrayList<Guardian> result = guardianService.getAllGuardians();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getNameAndSurname());
    }
}