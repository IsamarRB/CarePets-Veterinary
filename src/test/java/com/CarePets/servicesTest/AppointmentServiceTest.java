package com.CarePets.servicesTest;

import com.CarePets.repositories.IAppointmentRepository;
import com.CarePets.services.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class AppointmentServiceTest {

    @Mock
    private IAppointmentRepository iAppointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteAppointmentTest() {
        Long id = 1L;

        doNothing().when(iAppointmentRepository).deleteById(id);

        appointmentService.deleteAppointment(id);

        verify(iAppointmentRepository).deleteById(id);
    }
}