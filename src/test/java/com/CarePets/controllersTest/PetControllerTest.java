package com.CarePets.controllersTest;

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

public class PetControllerTest {

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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


