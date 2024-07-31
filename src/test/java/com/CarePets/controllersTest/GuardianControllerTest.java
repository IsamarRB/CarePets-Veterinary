package com.CarePets.controllersTest;
import com.CarePets.controllers.GuardianController;
import com.CarePets.models.Guardian;
import com.CarePets.services.GuardianService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GuardianControllerTest {

    @Mock
    private GuardianService guardianService;

    @InjectMocks
    private GuardianController guardianController;

    public GuardianControllerTest() {
        openMocks(this);
    }

    @Test
    public void testGetByGuardiansById() {
        Guardian guardian = new Guardian(1L, "John Doe", 123456789, null);
        when(guardianService.getByGuardiansById(1L)).thenReturn(Optional.of(guardian));

        ResponseEntity<Guardian> response = guardianController.getByGuardiansById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getNameAndSurname());
    }

    @Test
    public void testGetAllGuardians() {
        ArrayList<Guardian> guardians = new ArrayList<>();
        guardians.add(new Guardian(1L, "John Doe", 123456789, null));
        when(guardianService.getAllGuardians()).thenReturn(guardians);

        ArrayList<Guardian> result = guardianController.getAllGuardians();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getNameAndSurname());
    }
}