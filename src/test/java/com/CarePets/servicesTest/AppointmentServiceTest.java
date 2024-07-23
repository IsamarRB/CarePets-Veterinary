package com.CarePets.servicesTest;


import com.CarePets.models.Appointment;
import com.CarePets.models.Guardian;
import com.CarePets.models.Pet;
import com.CarePets.repositories.IAppointmentRepository;
import com.CarePets.services.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class AppointmentServiceTest {

    @Mock
    private IAppointmentRepository iAppointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;
    private Appointment appointment;
    private Pet pet;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_if_getAppointmentByType_gets_appointment(){
        //arrange
        List<Guardian> guardianList = new ArrayList<Guardian>();
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url",guardianList, appointmentList);
        Appointment ap1 = new Appointment(1L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita );
        Pet bolita2 = new Pet(2L, "bolita", 2, "demogorgon", "female", "url",guardianList, appointmentList);
        Appointment ap2 = new Appointment(2L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita );
        appointmentList.add(ap1);
        appointmentList.add(ap2);
        when(iAppointmentRepository.findAll()).thenReturn(appointmentList);


        //act
        ArrayList<Appointment> result = appointmentService.getAllAppointments();

        //assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("urgent", result.get(0).getTypeConsult());

    }
    @Test
    public void test_if_getAppointmentByName_gets_appointment(){
        //arrange
        List<Guardian> guardianList = new ArrayList<Guardian>();
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url",guardianList, appointmentList);
        Appointment ap1 = new Appointment(1L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita );
        Pet jaro = new Pet(2L, "jaro", 2, "golden retrieve", "male", "url",guardianList, appointmentList);
        Appointment ap2 = new Appointment(2L, LocalDateTime.now(), "urgent", "tummy ache", "past", jaro );
        appointmentList.add(ap1);
        appointmentList.add(ap2);
        when(iAppointmentRepository.findAll()).thenReturn(appointmentList);


        //act
        ArrayList<Appointment> result = appointmentService.getAllAppointments();

        //assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("jaro", result.get(1).getPet().getName());

    }
    @Test
    public void test_if_getAppointmentByStatus_gets_appointment(){
        //arrange
        List<Guardian> guardianList = new ArrayList<Guardian>();
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url",guardianList, appointmentList);
        Appointment ap1 = new Appointment(1L, LocalDateTime.now(), "standard", "tummy ache", "pending", bolita );
        Pet bolita2 = new Pet(2L, "bolita", 2, "demogorgon", "female", "url",guardianList, appointmentList);
        Appointment ap2 = new Appointment(2L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita );
        appointmentList.add(ap1);
        appointmentList.add(ap2);
        when(iAppointmentRepository.findAll()).thenReturn(appointmentList);


        //act
        ArrayList<Appointment> result = appointmentService.getAllAppointments();

        //assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("pending", result.get(0).getStatus());

    }
    @Test
    public void test_if_getAppointmentById_gets_appointment(){
        //arrange
        List<Guardian> guardianList = new ArrayList<Guardian>();
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url",guardianList, appointmentList);
        Appointment ap1 = new Appointment(1L, LocalDateTime.now(), "standard", "tummy ache", "pending", bolita );
        Pet bolita2 = new Pet(2L, "bolita", 2, "demogorgon", "female", "url",guardianList, appointmentList);
        Appointment ap2 = new Appointment(2L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita );
        appointmentList.add(ap1);
        appointmentList.add(ap2);
        when(iAppointmentRepository.findAll()).thenReturn(appointmentList);


        //act
        ArrayList<Appointment> result = appointmentService.getAllAppointments();

        //assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdAppointment());

    }
    @Test
    public void test_if_getAllAppointments_gets_a_list_of_appointments(){
        //arrange
        List<Guardian> guardianList = new ArrayList<Guardian>();
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url",guardianList, appointmentList);
        Appointment ap1 = new Appointment(1L, LocalDateTime.now(), "standard", "tummy ache", "pending", bolita );
        Pet bolita2 = new Pet(2L, "bolita", 2, "demogorgon", "female", "url",guardianList, appointmentList);
        Appointment ap2 = new Appointment(2L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita );
        appointmentList.add(ap1);
        appointmentList.add(ap2);
        when(iAppointmentRepository.findAll()).thenReturn(appointmentList);


        //act
        ArrayList<Appointment> result = appointmentService.getAllAppointments();

        //assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(iAppointmentRepository).findAll();
    }

}