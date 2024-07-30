package com.CarePets.servicesTest;

import com.CarePets.models.Pet;
import com.CarePets.repositories.IPetRepository;
import com.CarePets.services.PetService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.CarePets.models.Pet;
import com.CarePets.repositories.IPetRepository;
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

class PetServiceTest {
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public PetServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeletePet(){
        Pet petToDelete = new Pet();
        Long petId = 1L;

        when(iPetRepository.existsById(petId)).thenReturn(true);

        petService.deletePet(1L);

        verify(iPetRepository, times(1)).deleteById(petId);

    }

    @Test
    void listPet_shouldReturnAllPets() {
        Pet pet1 = new Pet(1L, "Gordita", 12, "Westie", "Female", "url");
        Pet pet2 = new Pet(2L, "Shazam", 10, "Pitbull", "Female", "url");
        when(iPetRepository.findAll()).thenReturn(Arrays.asList(pet1, pet2));

        List<Pet> pets = petService.listPet();

        assertEquals(2, pets.size());
        verify(iPetRepository, times(1)).findAll();
    }

    @Test
    void getPetById_shouldReturnPet() {
        Pet pet = new Pet(2L, "Shazam", 10, "Pitbull", "Female", "url");
        when(iPetRepository.findById(2L)).thenReturn(Optional.of(pet));

        Optional<Pet> foundPet = petService.getPetById(2L);

        assertTrue(foundPet.isPresent());
        assertEquals("Shazam", foundPet.get().getName());
        verify(iPetRepository, times(1)).findById(2L);
    }

    @Test
    void updatePet_shouldReturnUpdatedPet() {
        Pet pet = new Pet(3L, "Krispyn", 6, "American shorthair", "Female", "url");
        when(iPetRepository.save(pet)).thenReturn(pet);

        Pet updatedPet = petService.updatePet(pet);

        assertNotNull(updatedPet);
        assertEquals("Krispyn", updatedPet.getName());
        verify(iPetRepository, times(1)).save(pet);
    }

    @Test
    void addPet_shouldReturnAddedPet() {
        Pet pet = new Pet(4L, "Luffy", 4, "Westie", "Male", "url");
        when(iPetRepository.save(pet)).thenReturn(pet);

        Pet addedPet = petService.addPet(pet);

        assertNotNull(addedPet);
        assertEquals("Luffy", addedPet.getName());
    }
}