package com.CarePets.controllersTest;

import com.CarePets.controllers.PetController;
import com.CarePets.services.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
public class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService PetService;

    @Test
    public void deletePetTest() throws Exception {
        Long petId = 1L;


        doNothing().when(PetService).deletePet(petId);


        mockMvc.perform(delete("/pet/pets/{id}", petId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(PetService).deletePet(petId);
        }
    }

