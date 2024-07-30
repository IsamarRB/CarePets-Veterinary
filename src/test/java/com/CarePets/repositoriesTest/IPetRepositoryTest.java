package com.CarePets.repositoriesTest;

import com.CarePets.models.Pet;
import com.CarePets.repositories.IPetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest

class IPetRepositoryTest {
    @Autowired
    private IPetRepository petRepository;

    @Test
    void testSaveAndFindById() {
        Pet pet = new Pet(null, "Gordita", 12, "Westie", "Female", "http://example.com/gordita");
        Pet savedPet = petRepository.save(pet);

        Optional<Pet> retrievedPet = petRepository.findById(savedPet.getIdPet());
        assertTrue(retrievedPet.isPresent());
        assertEquals(savedPet.getIdPet(), retrievedPet.get().getIdPet());
        assertEquals("Gordita", retrievedPet.get().getName());
        assertEquals(12, retrievedPet.get().getAge());
        assertEquals("Westie", retrievedPet.get().getRace());
        assertEquals("Female", retrievedPet.get().getGender());
        assertEquals("http://example.com/gordita", retrievedPet.get().getUrl());
    }

    @Test
    void testFindAll() {
        Pet pet1 = new Pet(null, "Gordita", 12, "Westie", "Female", "http://example.com/gordita");
        Pet pet2 = new Pet(null, "Shazam", 10, "Pitbull", "Male", "http://example.com/shazam");
        petRepository.save(pet1);
        petRepository.save(pet2);

        Iterable<Pet> pets = petRepository.findAll();
        int count = 0;
        for (Pet pet : pets) {
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    void testDeleteById() {
        Pet pet = new Pet(null, "Gordita", 12, "Westie", "Female", "http://example.com/gordita");
        Pet savedPet = petRepository.save(pet);
        petRepository.deleteById(savedPet.getIdPet());

        Optional<Pet> deletedPet = petRepository.findById(savedPet.getIdPet());
        assertFalse(deletedPet.isPresent());
    }

    @Test
    void testUpdatePet() {
        Pet pet = new Pet(null, "Gordita", 12, "Westie", "Female", "http://example.com/gordita");
        Pet savedPet = petRepository.save(pet);

        savedPet.setName("Gordita Updated");
        savedPet.setAge(13);
        Pet updatedPet = petRepository.save(savedPet);

        Optional<Pet> retrievedPet = petRepository.findById(updatedPet.getIdPet());
        assertTrue(retrievedPet.isPresent());
        assertEquals("Gordita Updated", retrievedPet.get().getName());
        assertEquals(13, retrievedPet.get().getAge());
    }
}

