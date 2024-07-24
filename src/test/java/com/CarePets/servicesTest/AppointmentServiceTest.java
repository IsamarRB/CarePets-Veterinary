package com.CarePets.servicesTest;

import com.CarePets.models.Appointment;
import com.CarePets.repositories.IAppointmentRepository;
import com.CarePets.services.AppointmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class AppointmentServiceTest {

    @Mock
    private IAppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    public AppointmentServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateAppointment() {
        Appointment appointment = new Appointment();
        appointment.setIdAppointment(1L);

        appointmentService.updateAppointment(appointment, 1L);

        verify(appointmentRepository, times(1)).save(appointment);
    }
}