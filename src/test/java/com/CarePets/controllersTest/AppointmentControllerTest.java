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
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import com.CarePets.dto.CreateAppointmentRequest;
import com.CarePets.models.Guardian;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AppointmentController.class)

public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private AppointmentService appointmentService;
    private ObjectMapper objectMapper;

    @InjectMocks
    private AppointmentController appointmentController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
        objectMapper = new ObjectMapper();
    }
    @Test
    public void testCreateAppointment_when_create_appointment() throws Exception {
        CreateAppointmentRequest request = new CreateAppointmentRequest();
        request.setIdPet(1L);
        request.setDateTime(LocalDateTime.of(2024, 7, 25, 10, 0));
        request.setTypeConsult("standard");
        request.setReason("annual check up");
        request.setStatus("past");

        Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url", null, null);
        Appointment newAppointment = new Appointment();
        newAppointment.setPet(bolita);
        newAppointment.setDateTime(request.getDateTime());
        newAppointment.setTypeConsult(request.getTypeConsult());
        newAppointment.setReason(request.getReason());
        newAppointment.setStatus(request.getStatus());

        when(appointmentService.createAppoinment(request)).thenReturn(newAppointment);


        Appointment createdAppointment = appointmentController.createAppointment(request);
        assertEquals(bolita, createdAppointment.getPet());
        assertEquals(request.getDateTime(), createdAppointment.getDateTime());
        assertEquals(request.getTypeConsult(), createdAppointment.getTypeConsult());
        assertEquals(request.getReason(), createdAppointment.getReason());
        assertEquals(request.getStatus(), createdAppointment.getStatus());

    }

    @Test
    public void test_if_getAppointmentByType_gets_appointment() {
        //arrange
        List<Guardian> guardianList = new ArrayList<Guardian>();
        ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
        Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url", guardianList, appointmentList);
        Appointment ap1 = new Appointment(1L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita);
        Pet bolita2 = new Pet(2L, "bolita", 2, "demogorgon", "female", "url", guardianList, appointmentList);
        Appointment ap2 = new Appointment(2L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita);
        appointmentList.add(ap1);
        appointmentList.add(ap2);

        when(appointmentService.getAppointmentByType("urgent")).thenReturn(appointmentList);

        //act
        List<Appointment> result2 = appointmentController.getAppointmentByType("urgent");

        //assert
        assertEquals("urgent", result2.get(0).getTypeConsult());
    }
    @Test
    public void test_if_getAppointmentByStatus_gets_appointment() {
        //arrange
        List<Guardian> guardianList = new ArrayList<Guardian>();
        ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
        Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url", guardianList, appointmentList);
        Appointment ap1 = new Appointment(1L, LocalDateTime.now(), "standard", "tummy ache", "pending", bolita);
        Pet bolita2 = new Pet(2L, "bolita", 2, "demogorgon", "female", "url", guardianList, appointmentList);
        Appointment ap2 = new Appointment(2L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita);
        appointmentList.add(ap1);
        appointmentList.add(ap2);

        when(appointmentService.getAppointmentByStatus("pending")).thenReturn(appointmentList);

        //act
        List<Appointment> result2 = appointmentController.getAppointmentByStatus("pending");

        //assert
        assertEquals("pending", result2.get(0).getStatus());

    }
    @Test
    public void test_if_getAppointmentById_gets_appointment() {
        //arrange
        List<Guardian> guardianList = new ArrayList<Guardian>();
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url", guardianList, appointmentList);
        Appointment ap1 = new Appointment(1L, LocalDateTime.now(), "standard", "tummy ache", "pending", bolita);
        Pet bolita2 = new Pet(2L, "bolita", 2, "demogorgon", "female", "url", guardianList, appointmentList);
        Appointment ap2 = new Appointment(2L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita);
        appointmentList.add(ap1);
        appointmentList.add(ap2);
        when(appointmentController.getAllAppointments()).thenReturn(appointmentList);
        when(appointmentController.getAppointmentById(1L)).thenReturn(Optional.of(ap1));

        //act
        List<Appointment> result = appointmentController.getAllAppointments();
        Optional result2 = appointmentController.getAppointmentById(1L);

        //assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdAppointment());
        assertEquals(Optional.of(ap1), appointmentController.getAppointmentById(1L));

    }
    @Test
    public void test_if_getAllAppointments_gets_a_list_of_appointments() {
        //arrange
        List<Guardian> guardianList = new ArrayList<Guardian>();
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url", guardianList, appointmentList);
        Appointment ap1 = new Appointment(1L, LocalDateTime.now(), "standard", "tummy ache", "pending", bolita);
        Pet bolita2 = new Pet(2L, "bolita", 2, "demogorgon", "female", "url", guardianList, appointmentList);
        Appointment ap2 = new Appointment(2L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita);
        appointmentList.add(ap1);
        appointmentList.add(ap2);
        when(appointmentController.getAllAppointments()).thenReturn(appointmentList);

        //act
        List<Appointment> result = appointmentController.getAllAppointments();

        //assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(appointmentService).getAllAppointments();
    }
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


    @Test
    void deleteAppointmentTest() throws Exception {
        Long id = 1L;

        doNothing().when(appointmentService).deleteAppointment(id);

        mockMvc.perform(delete("/appointment/appointments/{id}", id))
                .andExpect(status().isOk());

        verify(appointmentService).deleteAppointment(id);
    }
}