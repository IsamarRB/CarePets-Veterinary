package com.CarePets.controllersTest;

import com.CarePets.controllers.GuardianController;
import com.CarePets.models.Guardian;
import com.CarePets.models.Pet;
import com.CarePets.services.GuardianService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

public class GuardianControllerTest {

    @Mock
    private GuardianService guardianService;

    @InjectMocks
    private GuardianController guardianController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(guardianController).build();
    }
    @Test
    void test_if_updateGuardian_updates_information() throws Exception {
        // Arrange
        Long id = 1L;
        Pet bolita = new Pet();
        Guardian oldGuardian = new Guardian(id, "Joselitro García", 123456789, bolita);
        Guardian newGuardian = new Guardian(id, "Kid A", 987654321, bolita);

        when(guardianService.updateGuardian(anyLong(), any(Guardian.class))).thenReturn(newGuardian);

        ObjectMapper objectMapper = new ObjectMapper();
        String newGuardianJson = objectMapper.writeValueAsString(newGuardian);

        // Act & Assert
        mockMvc.perform(put("/guardian/guardians/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newGuardianJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameAndSurname", is("Kid A")))
                .andExpect(jsonPath("$.telephoneNumber", is(987654321)));
    }

}
