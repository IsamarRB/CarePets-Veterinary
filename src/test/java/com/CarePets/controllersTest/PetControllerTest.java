package com.CarePets.controllersTest;

import com.CarePets.controllers.PetController;
import com.CarePets.services.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.CarePets.exceptions.PetNotFoundException;
import com.CarePets.models.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(PetController.class)

public class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @InjectMocks
    private PetController petController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(petController)
                .build();
    }

    @Test
    public void deletePetTest() throws Exception {
        Long petId = 1L;


        doNothing().when(petService).deletePet(petId);


        mockMvc.perform(delete("/pet/pets/{id}", petId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(petService).deletePet(petId);
    }

    @Test
    void listPet_shouldReturnListOfPets() {
        Pet pet1 = new Pet(1L, "Gordita", 12, "Westie", "Female", "url");
        Pet pet2 = new Pet(2L, "Shazam", 10, "Pitbull", "Female", "url");
        when(petService.getAllPets()).thenReturn(Arrays.asList(pet1, pet2));

        List<Pet> result = petController.getAllPets();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(petService, times(1)).getAllPets();
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
    @Test
    public void testAddNewPet() throws Exception {
        Pet pet = new Pet();
        pet.setIdPet(1L);
        pet.setName("Balud");
        pet.setAge(5);
        pet.setRace("Golden Retriever");
        pet.setGender("Male");
        pet.setUrl("http://example.com/balud.jpg");

        when(petService.addNewPet(any(Pet.class))).thenReturn(pet);

        ObjectMapper objectMapper = new ObjectMapper();
        String petJson = objectMapper.writeValueAsString(pet);

        ResultActions resultActions = mockMvc.perform(post("/pet/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(petJson));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.idPet").value(1L))
                .andExpect(jsonPath("$.name").value("Balud"))
                .andExpect(jsonPath("$.age").value(5))
                .andExpect(jsonPath("$.race").value("Golden Retriever"))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.url").value("http://example.com/balud.jpg"));
    }
}
