package com.CarePets.controllersTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.CarePets.controllers.AppointmentController;
import com.CarePets.models.Appointment;
import com.CarePets.models.Pet;
import com.CarePets.services.AppointmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testUpdateAppointment() throws Exception {
        Long id = 1L;

        Pet pet = new Pet();
        pet.setIdPet(1L);

        Appointment appointment = new Appointment();
        appointment.setIdAppointment(id);
        appointment.setDateTime(LocalDateTime.now());
        appointment.setTypeConsult("standard");
        appointment.setReason("Regular Checkup");
        appointment.setStatus("Past");
        appointment.setPet(pet);

        Mockito.doNothing().when(appointmentService).updateAppointment(Mockito.any(Appointment.class), Mockito.eq(id));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc.perform(put("/appointment/appointments/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appointment)))
                .andExpect(status().isOk());

        Mockito.verify(appointmentService, Mockito.times(1)).updateAppointment(Mockito.any(Appointment.class), Mockito.eq(id));
    }
}