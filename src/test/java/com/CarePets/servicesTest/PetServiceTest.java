package com.CarePets.servicesTest;

import com.CarePets.models.Pet;
import com.CarePets.repositories.IPetRepository;
import com.CarePets.services.PetService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class PetServiceTest {
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private PetService petService;


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
}
