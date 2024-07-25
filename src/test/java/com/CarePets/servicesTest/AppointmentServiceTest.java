package com.CarePets.servicesTest;

import com.CarePets.models.Appointment;
import com.CarePets.repositories.IAppointmentRepository;
import com.CarePets.services.AppointmentService;
import jakarta.persistence.Column;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class AppointmentServiceTest {

    @Mock
    private IAppointmentRepository iappointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    public AppointmentServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateAppointment() {
        Appointment updateappointment = new Appointment();
        Long idAppointment = 1L;
        LocalDateTime dateTime = LocalDateTime.now();
        String typeConsult = "standard";
        String reason = "he feels bad";
        String status= "Past";

        updateappointment.setIdAppointment(idAppointment);
        updateappointment.setDateTime(dateTime);
        updateappointment.setTypeConsult(typeConsult);
        updateappointment.setReason(reason);
        updateappointment.setStatus(status);

        when(iappointmentRepository.save(updateappointment)).thenReturn(updateappointment);

        appointmentService.updateAppointment();


        verify(iappointmentRepository, times(1)).save(updateappointment);
    }
}
