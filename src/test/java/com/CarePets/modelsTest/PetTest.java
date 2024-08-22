package com.CarePets.modelsTest;

import com.CarePets.models.Pet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PetTest {
    @Test
    void testPetConstructorAndGetters() {
        Pet pet = new Pet(1L, "Gordita", 12, "Westie", "Female", "http://example.com/gordita");

        assertEquals(1L, pet.getIdPet());
        assertEquals("Gordita", pet.getName());
        assertEquals(12, pet.getAge());
        assertEquals("Westie", pet.getRace());
        assertEquals("Female", pet.getGender());
        assertEquals("http://example.com/gordita", pet.getUrl());
    }

    @Test
    void testSetters() {
        Pet pet = new Pet();
        pet.setIdPet(2L);
        pet.setName("Shazam");
        pet.setAge(10);
        pet.setRace("Pitbull");
        pet.setGender("Male");
        pet.setUrl("http://example.com/shazam");

        assertEquals(2L, pet.getIdPet());
        assertEquals("Shazam", pet.getName());
        assertEquals(10, pet.getAge());
        assertEquals("Pitbull", pet.getRace());
        assertEquals("Male", pet.getGender());
        assertEquals("http://example.com/shazam", pet.getUrl());
    }

    @Test
    void testCopyConstructor() {
        Pet originalPet = new Pet(1L, "Gordita", 12, "Westie", "Female", "http://example.com/gordita");
        Pet copiedPet = new Pet(originalPet);

        assertEquals(originalPet.getIdPet(), copiedPet.getIdPet());
        assertEquals(originalPet.getName(), copiedPet.getName());
        assertEquals(originalPet.getAge(), copiedPet.getAge());
        assertEquals(originalPet.getRace(), copiedPet.getRace());
        assertEquals(originalPet.getGender(), copiedPet.getGender());
        assertEquals(originalPet.getUrl(), copiedPet.getUrl());
    }

    @Test
    void testUpdate() {
        Pet originalPet = new Pet(1L, "Gordita", 12, "Westie", "Female", "http://example.com/gordita");
        Pet updatedPet = new Pet(1L, "Gordita Updated", 13, "Westie", "Female", "http://example.com/gordita-updated");

        originalPet.update(updatedPet);

        assertEquals("Gordita Updated", originalPet.getName());
        assertEquals(13, originalPet.getAge());
        assertEquals("Westie", originalPet.getRace());
        assertEquals("Female", originalPet.getGender());
        assertEquals("http://example.com/gordita-updated", originalPet.getUrl());
    }
}