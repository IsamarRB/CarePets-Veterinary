package com.CarePets.controllersTest;

import com.CarePets.controllers.GuardianController;
import com.CarePets.services.GuardianService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.CarePets.models.Guardian;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.CarePets.models.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.hamcrest.Matchers.is;


class GuardianControllerTest {
@ExtendWith(MockitoExtension.class)

    private MockMvc mockMvc;

    @Mock
    private GuardianService guardianService;

    @InjectMocks
    private GuardianController guardianController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(guardianController).build();
    }

    @Test
    void deleteGuardian_shouldDeleteGuardian() throws Exception {
        mockMvc.perform(delete("/api/guardian/1"))
                .andExpect(status().isOk());

        verify(guardianService, times(1)).deleteGuardian(1L);
    }


    @Test
    public void testCreateGuardian() throws Exception {
        Guardian guardian = new Guardian();
        guardian.setIdGuardian(1L);
        guardian.setNameAndSurname("John Doe");
        guardian.setTelephoneNumber(123456789);

        when(guardianService.createGuardian(any(Guardian.class))).thenReturn(guardian);

        Guardian createdGuardian = guardianService.createGuardian(guardian);

        assertNotNull(createdGuardian);
        assertEquals(1L, createdGuardian.getIdGuardian());
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