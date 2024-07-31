package com.CarePets.controllersTest;

import com.CarePets.controllers.AppointmentController;
import com.CarePets.services.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class AppointmentControllerTest {
    private MockMvc mockMvc;

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @Test
    void deleteAppointmentTest() throws Exception {
        Long id = 1L;

        doNothing().when(appointmentService).deleteAppointment(id);

        mockMvc.perform(delete("/appointment/appointments/{id}", id))
                .andExpect(status().isOk());

        verify(appointmentService).deleteAppointment(id);
    }
}


