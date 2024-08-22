package com.CarePets.modelsTest;

import com.CarePets.models.Appointment;
import com.CarePets.models.Pet;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    @Test
    public void testAppointmentModel() {
        LocalDateTime dateTime = LocalDateTime.now();
        Pet pet = new Pet();
        Appointment appointment = new Appointment(1L, dateTime, "standard", "checkup", "pending", pet);

        assertEquals(1L, appointment.getIdAppointment());
        assertEquals(dateTime, appointment.getDateTime());
        assertEquals("standard", appointment.getTypeConsult());
        assertEquals("checkup", appointment.getReason());
        assertEquals("pending", appointment.getStatus());
        assertEquals(pet, appointment.getPet());
    }
}