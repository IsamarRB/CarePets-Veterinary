package com.CarePets.servicesTest;


import com.CarePets.dto.CreateAppointmentRequest;
import com.CarePets.models.Appointment;
import com.CarePets.models.Guardian;
import com.CarePets.models.Pet;
import com.CarePets.repositories.IAppointmentRepository;
import com.CarePets.repositories.IPetRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class AppointmentServiceTest {

    @Mock
    private IAppointmentRepository iAppointmentRepository;
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private AppointmentService appointmentService;
    private Appointment appointment;
    private Pet pet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


@Test
public void test_createAppointment() throws Exception {
    CreateAppointmentRequest request = new CreateAppointmentRequest();
    request.setIdPet(1L);
    request.setDateTime(LocalDateTime.of(2024, 7, 25, 10, 0));
    request.setTypeConsult("standard");
    request.setReason("annual check up");
    request.setStatus("past");

    List<Guardian> guardianList = new ArrayList<>();
    List<Appointment> appointmentList = new ArrayList<>();
    Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url", guardianList, appointmentList);
    Appointment newAppointment = new Appointment();
    newAppointment.setPet(bolita);
    newAppointment.setDateTime(request.getDateTime());
    newAppointment.setTypeConsult(request.getTypeConsult());
    newAppointment.setReason(request.getReason());
    newAppointment.setStatus(request.getStatus());

    when(iPetRepository.findById(1L)).thenReturn(Optional.of(bolita));
    when(iAppointmentRepository.save(any(Appointment.class))).thenReturn(newAppointment);


    Appointment createdAppointment = appointmentService.createAppoinment(request);
    assertEquals(bolita, createdAppointment.getPet());
    assertEquals(request.getDateTime(),createdAppointment.getDateTime());
    assertEquals(request.getTypeConsult(), createdAppointment.getTypeConsult());
    assertEquals(request.getReason(), createdAppointment.getReason());
    assertEquals(request.getStatus(), createdAppointment.getStatus());
}

@Test
public void test_if_getAppointmentByType_gets_appointment() {
    //arrange
    List<Guardian> guardianList = new ArrayList<Guardian>();
    List<Appointment> appointmentList = new ArrayList<Appointment>();
    Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url", guardianList, appointmentList);
    Appointment ap1 = new Appointment(1L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita);
    Appointment ap2 = new Appointment(2L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita);
    appointmentList.add(ap1);
    appointmentList.add(ap2);
    when(iAppointmentRepository.findAll()).thenReturn(appointmentList);
    when(iAppointmentRepository.findByTypeConsult("urgent")).thenReturn(ap1);

    //act
    ArrayList<Appointment> result = appointmentService.getAllAppointments();
    Appointment result2 = appointmentService.getAppointmentByType("urgent");


    //assert
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("urgent", result.get(0).getTypeConsult());
    assertEquals("urgent", result2.getTypeConsult());

}

@Test
public void test_if_getAppointmentByStatus_gets_appointment() {
    //arrange
    List<Guardian> guardianList = new ArrayList<Guardian>();
    List<Appointment> appointmentList = new ArrayList<Appointment>();
    Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url", guardianList, appointmentList);
    Appointment ap1 = new Appointment(1L, LocalDateTime.now(), "standard", "tummy ache", "pending", bolita);
    Pet bolita2 = new Pet(2L, "bolita", 2, "demogorgon", "female", "url", guardianList, appointmentList);
    Appointment ap2 = new Appointment(2L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita);
    appointmentList.add(ap1);
    appointmentList.add(ap2);
    when(iAppointmentRepository.findAll()).thenReturn(appointmentList);
    when(iAppointmentRepository.findByStatus("past")).thenReturn(ap2);


    //act
    ArrayList<Appointment> result = appointmentService.getAllAppointments();
    Appointment result2 = appointmentService.getAppointmentByStatus("past");

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
    when(iAppointmentRepository.findAll()).thenReturn(appointmentList);
    when(iAppointmentRepository.findById(1L)).thenReturn(Optional.of(ap1));


    //act
    ArrayList<Appointment> result = appointmentService.getAllAppointments();
    Optional result2 = appointmentService.getAppointmentById(1L);

    //assert
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(1, result.get(0).getIdAppointment());
    assertEquals(Optional.of(ap1), appointmentService.getAppointmentById(1L));

}

@Test
public void test_if_getAllAppointments_gets_a_list_of_appointments() {
    //arrange
    List<Guardian> guardianList = new ArrayList<Guardian>();
    List<Appointment> appointmentList = new ArrayList<Appointment>();
    Pet bolita = new Pet(1L, "bolita", 2, "demogorgon", "female", "url", guardianList, appointmentList);
    Appointment ap1 = new Appointment(1L, LocalDateTime.now(), "standard", "tummy ache", "pending", bolita);
    Appointment ap2 = new Appointment(2L, LocalDateTime.now(), "urgent", "tummy ache", "past", bolita);
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
    @Test
    public void testAvailableDate_when_date_is_available(){
        LocalDateTime dateTime = LocalDateTime.of (2024, 7, 25, 10, 0);
        List <Appointment> appointments = new ArrayList<>();
        when(iAppointmentRepository.findAll()).thenReturn(appointments);

    //act
        boolean isAvailable = appointmentService.availableDate(dateTime);

        assertTrue(isAvailable);
    }
    @Test
    public void testAvailableDate_when_date_is_not_available(){
        LocalDateTime dateTime = LocalDateTime.of (2024, 7, 25, 10, 0);
        List <Appointment> appointments = new ArrayList<>();
        when(iAppointmentRepository.findAll()).thenReturn(appointments);

        //act
        boolean isAvailable = appointmentService.availableDate(dateTime);

        assertFalse(isAvailable);
    }

}