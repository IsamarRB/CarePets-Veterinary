package com.CarePets.servicesTest;

import com.CarePets.models.Pet;
import com.CarePets.repositories.IPetRepository;
import com.CarePets.services.PetService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PetServiceTest {

    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private PetService petService;

    public PetServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewPet() {
        Pet pet = new Pet();
        pet.setIdPet(1L);
        pet.setName("Balud");
        pet.setAge(5);
        pet.setRace("Golden Retriever");
        pet.setGender("Male");
        pet.setUrl("http://example.com/balud.jpg");

        when(iPetRepository.save(any(Pet.class))).thenReturn(pet);

        Pet result = petService.addNewPet(pet);

        assertEquals(1L, result.getIdPet());
        assertEquals("Balud", result.getName());
        assertEquals(5, result.getAge());
        assertEquals("Golden Retriever", result.getRace());
        assertEquals("Male", result.getGender());
        assertEquals("http://example.com/balud.jpg", result.getUrl());
    }
}