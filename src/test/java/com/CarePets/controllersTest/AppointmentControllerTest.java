package com.CarePets.controllersTest;

import com.CarePets.controllers.AppointmentController;
import com.CarePets.models.Appointment;
import com.CarePets.services.AppointmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Test
    public void testUpdateAppointment() throws Exception {
        Appointment appointment = new Appointment(1L, LocalDateTime.now(), "standard", "checkup", "pending", null);

        Mockito.doNothing().when(appointmentService).updateAppointment(Mockito.any(Appointment.class), Mockito.eq(1L));

        mockMvc.perform(put("/appointment/appointments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(appointment)))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}