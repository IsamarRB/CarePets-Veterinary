package com.CarePets.modelsTest;

import com.CarePets.models.Guardian;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GuardianTest {

    @Test
    void testGuardianSetters() {
        Guardian guardian = new Guardian();
        guardian.setIdGuardian(1L);
        guardian.setNameAndSurname("Alessia");
        guardian.setTelephoneNumber(123456789);

        assertEquals(1L, guardian.getIdGuardian());
        assertEquals("Alessia", guardian.getNameAndSurname());
        assertEquals(123456789, guardian.getTelephoneNumber());
    }

    @Test
    void testDefaultConstructor() {
        Guardian guardian = new Guardian();
        assertNull(guardian.getIdGuardian());
        assertNull(guardian.getNameAndSurname());
        assertEquals(0, guardian.getTelephoneNumber());
    }
}