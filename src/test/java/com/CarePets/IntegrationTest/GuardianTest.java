package com.CarePets.IntegrationTest;

import com.CarePets.models.Pet;
import com.CarePets.repositories.IPetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class GuardianControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private IPetRepository petRepository;

    @Test
    void testGetPetById() {
        Pet pet = new Pet();
        pet.setName("Rex");
        pet = petRepository.save(pet);

        ResponseEntity<Pet> response = restTemplate.getForEntity("/api/guardians/pets/" + pet.getIdPet(), Pet.class);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Rex", response.getBody().getName());
    }

    @Test
    void testUpdatePet() {
        Pet pet = new Pet();
        pet.setName("Old Name");
        pet = petRepository.save(pet);

        Pet updatedDetails = new Pet();
        updatedDetails.setName("New Name");

        HttpEntity<Pet> request = new HttpEntity<>(updatedDetails);
        ResponseEntity<Pet> response = restTemplate.exchange("/api/guardians/pets/" + pet.getIdPet(), HttpMethod.PUT, request, Pet.class);

        assertEquals(200, response.getStatusCodeValue());
        Pet updatedPet = response.getBody();
        assertNotNull(updatedPet);
        assertEquals("New Name", updatedPet.getName());
    }
}
