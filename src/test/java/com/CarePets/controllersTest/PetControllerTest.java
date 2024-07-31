package com.CarePets.controllersTest;

import com.CarePets.controllers.PetController;
import com.CarePets.models.Pet;
import com.CarePets.services.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PetController.class)
public class PetControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(petController)
                .build();
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