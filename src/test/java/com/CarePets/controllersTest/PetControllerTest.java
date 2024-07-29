package com.CarePets.controllersTest;

import com.CarePets.controllers.PetController;
import com.CarePets.models.Pet;
import com.CarePets.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PetControllerTest {

        @Mock
        private PetService petService;

        @InjectMocks
        private PetController petController;

        private MockMvc mockMvc;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
        }

    @Test
    void listPet_shouldReturnListOfPets() throws Exception {
        Pet pet1 = new Pet(1L, "Gordita", 12, "Westie", "Female", "url");
        Pet pet2 = new Pet(2L, "Shazam", 10, "Pitbull", "Female", "url");
        when(petService.listPet()).thenReturn(Arrays.asList(pet1, pet2));

        mockMvc.perform(get("/api/pet/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Gordita"))
                .andExpect(jsonPath("$[1].name").value("Shazam"));


        verify(petService, times(1)).listPet();
    }

    @Test
        void updatePet_shouldReturnUpdatedPet() throws Exception {
            Pet pet = new Pet(1L, "Gordita", 12, "Westie", "Female", "url");
            Pet updatedPet = new Pet(1L, "Gordita Updated", 13, "Westie", "Female", "url");
            when(petService.getPetById(1L)).thenReturn(Optional.of(pet));
            when(petService.updatePet(any(Pet.class))).thenReturn(updatedPet);

            mockMvc.perform(put("/api/pet/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"name\":\"Gordita Updated\", \"age\":13, \"race\":\"Westie\", \"gender\":\"Female\", \"url\":\"url\"}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value("Gordita Updated"))
                    .andExpect(jsonPath("$.age").value(13));

            verify(petService, times(1)).getPetById(1L);
            verify(petService, times(1)).updatePet(any(Pet.class));
        }

        @Test
        void updatePet_whenPetNotFound_shouldReturnNotFound() throws Exception {
            when(petService.getPetById(1L)).thenReturn(Optional.empty());

            mockMvc.perform(put("/api/pet/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"name\":\"Gordita Updated\", \"age\":13, \"race\":\"Westie\", \"gender\":\"Female\", \"url\":\"url\"}"))
                    .andExpect(status().isNotFound());

            verify(petService, times(1)).getPetById(1L);
        }
    }

