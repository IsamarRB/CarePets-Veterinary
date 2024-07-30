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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Appointment updateAppointment = new Appointment();
        Long idAppointment = 1L;
        LocalDateTime dateTime = LocalDateTime.now();
        String typeConsult = "standard";
        String reason = "he feels bad";
        String status= "Past";

        updateAppointment.setIdAppointment(idAppointment);
        updateAppointment.setDateTime(dateTime);
        updateAppointment.setTypeConsult(typeConsult);
        updateAppointment.setReason(reason);
        updateAppointment.setStatus(status);

        appointmentService.updateAppointment(updateAppointment, idAppointment);


        verify(iappointmentRepository, times(1)).save(updateAppointment);
    }
}
