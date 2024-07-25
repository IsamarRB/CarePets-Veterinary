package com.CarePets.controllersTest;

import com.CarePets.controllers.AppointmentController;
import com.CarePets.models.Appointment;
import com.CarePets.models.Guardian;
import com.CarePets.models.Pet;
import com.CarePets.repositories.IAppointmentRepository;
import com.CarePets.services.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AppointmentControllerTest {


    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;
    private Appointment appointment;
    private Pet pet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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
        when(appointmentService.getAllAppointments()).thenReturn(appointmentList);
        when(appointmentService.getAppointmentByType("urgent")).thenReturn(ap1);

        //act
        List<Appointment> result = appointmentController.getAllAppointments();
        Appointment result2 = appointmentController.getAppointmentByType("urgent");


        //assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("urgent", result.get(0).getTypeConsult());
        assertEquals("urgent", result2.getTypeConsult());

    }

/*@Test
public void test_if_getAppointmentByName_gets_appointment() {
    //arrange
    List<Guardian> guardianList = new ArrayList<Guardian>();
    List<Appointment> appointmentList = new ArrayList<Appointment>();
    Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url", guardianList, appointmentList);
    Appointment ap1 = new Appointment(1L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita);
    Pet jaro = new Pet(2L, "jaro", 2, "golden retrieve", "male", "url", guardianList, appointmentList);
    Appointment ap2 = new Appointment(2L, LocalDateTime.now(), "urgent", "tummy ache", "past", jaro);
    appointmentList.add(ap1);
    appointmentList.add(ap2);
    when(iAppointmentRepository.findAll()).thenReturn(appointmentList);
    when(iAppointmentRepository.findByName("jaro")).thenReturn(ap2);

    //act
    ArrayList<Appointment> result = appointmentService.getAllAppointments();
    Appointment result2 = appointmentService.getAppointmentByName(jaro);


    //assert
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("jaro", result.get(1).getPet().getName());
    assertEquals("jaro", result2.getPet().getName());

}*/

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
        when(appointmentService.getAllAppointments()).thenReturn(appointmentList);
        when(appointmentService.getAppointmentByStatus("past")).thenReturn(ap2);


        //act
        List<Appointment> result = appointmentController.getAllAppointments();
        Appointment result2 = appointmentController.getAppointmentByStatus("past");

        //assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("pending", result.get(0).getStatus());
        assertEquals("past", result2.getStatus());

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
}

