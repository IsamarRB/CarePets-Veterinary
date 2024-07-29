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

class GuardianControllerTest {

    @Mock
    private GuardianService guardianService;

    @InjectMocks
    private GuardianController guardianController;

    private MockMvc mockMvc;

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
