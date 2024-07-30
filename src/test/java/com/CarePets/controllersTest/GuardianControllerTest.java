package com.CarePets.controllersTest;

import com.CarePets.controllers.GuardianController;
import com.CarePets.services.GuardianService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.CarePets.controllers.GuardianController;
import com.CarePets.models.Guardian;
import com.CarePets.services.GuardianService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GuardianControllerTest {

    @Mock
    private GuardianService guardianService;

    @InjectMocks
    private GuardianController guardianController;

    private MockMvc mockMvc;

@ExtendWith(MockitoExtension.class)
public class GuardianControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GuardianService guardianService;

    @InjectMocks
    private GuardianController guardianController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(guardianController).build();
    }
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
}

    @Test
    public void testCreateGuardian() throws Exception {
        Guardian guardian = new Guardian();
        guardian.setIdGuardian(1L);
        guardian.setNameAndSurname("John Doe");
        guardian.setTelephoneNumber(123456789);

        when(guardianService.createGuardian(any(Guardian.class))).thenReturn(guardian);

        String guardianJson = "{\"nameAndSurname\":\"John Doe\", \"telephoneNumber\":123456789, \"pet\":{\"idPet\":1}}";

        mockMvc.perform(post("/guardian/guardians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(guardianJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idGuardian").value(1L))
                .andExpect(jsonPath("$.nameAndSurname").value("John Doe"))
                .andExpect(jsonPath("$.telephoneNumber").value(123456789));
    }
}