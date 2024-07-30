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
import com.CarePets.controllers.PetController;
import com.CarePets.exceptions.PetNotFoundException;
import com.CarePets.models.Pet;
import com.CarePets.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@WebMvcTest(PetController.class)

public class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService PetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
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
    @Test
    void listPet_shouldReturnListOfPets() {
        Pet pet1 = new Pet(1L, "Gordita", 12, "Westie", "Female", "url");
        Pet pet2 = new Pet(2L, "Shazam", 10, "Pitbull", "Female", "url");
        when(petService.listPet()).thenReturn(Arrays.asList(pet1, pet2));

        List<Pet> result = petController.listPet();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(petService, times(1)).listPet();
    }

    @Test
    void updatePet_shouldReturnUpdatedPet() {
        Pet existingPet = new Pet(1L, "Gordita", 12, "Westie", "Female", "url");
        Pet updatedPetDetails = new Pet(1L, "Gordita Updated", 13, "Westie", "Female", "url");

        when(petService.getPetById(1L)).thenReturn(Optional.of(existingPet));
        when(petService.updatePet(existingPet)).thenReturn(updatedPetDetails);

        Pet result = petController.updatePet(1L, updatedPetDetails);

        assertEquals("Gordita Updated", result.getName());
        assertEquals(13, result.getAge());
        verify(petService, times(1)).getPetById(1L);
        verify(petService, times(1)).updatePet(existingPet);
    }

    @Test
    void updatePet_whenPetNotFound_shouldThrowException() {
        Pet updatedPetDetails = new Pet(1L, "Gordita Updated", 13, "Westie", "Female", "url");

        when(petService.getPetById(1L)).thenReturn(Optional.empty());

        assertThrows(PetNotFoundException.class, () -> {
            petController.updatePet(1L, updatedPetDetails);
        });

        verify(petService, times(1)).getPetById(1L);
    }
}


