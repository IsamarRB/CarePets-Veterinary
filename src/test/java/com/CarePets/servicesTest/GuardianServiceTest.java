package com.CarePets.servicesTest;

import com.CarePets.repositories.IGuardianRepository;
import com.CarePets.services.GuardianService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class GuardianServiceTest {

        @Mock
        private IGuardianRepository guardianRepository;

        @InjectMocks
        private GuardianService guardianService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void deleteGuardian_shouldDeleteGuardian() {
            Long id = 1L;
            guardianService.deleteGuardian(id);
            verify(guardianRepository, times(1)).deleteById(id);
        }
    }
