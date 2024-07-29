package com.CarePets.controllersTest;

import com.CarePets.controllers.GuardianController;
import com.CarePets.models.Guardian;
import com.CarePets.models.Pet;
import com.CarePets.services.GuardianService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GuardianControllerTest {

    @Mock
    private GuardianService guardianService;

    @InjectMocks
    private GuardianController guardianController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    void test_if_updateGuardian_updates_information(){
        Long id = 1L;
        Pet bolita = new Pet();
        Guardian oldGuardian = new Guardian(id, "Joselitro García", 123456789, bolita);
        Guardian newGuardian = new Guardian(id, "Kid A", 987654321, bolita);


    }

}
