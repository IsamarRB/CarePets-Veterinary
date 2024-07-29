package com.CarePets.repositoriesTest;

import com.CarePets.models.Guardian;
import com.CarePets.repositories.IGuardianRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collection;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IGuardianRepositoryTest {

        @Autowired
        private IGuardianRepository guardianRepository;

        @Test
        void testSaveAndFindById() {
            Guardian guardian = new Guardian(1L, "Alessia", 123456787);
            Guardian savedGuardian = guardianRepository.save(guardian);

            Optional<Guardian> foundGuardian = guardianRepository.findById(savedGuardian.getIdGuardian());
            assertTrue(foundGuardian.isPresent());
            assertEquals("Alessia", foundGuardian.get().getNameAndSurname());
            assertEquals(123456787, foundGuardian.get().getTelephoneNumber());
        }

        @Test
        void testFindAll() {
            Guardian guardian1 = new Guardian(2L, "Adriel", 123456788);
            Guardian guardian2 = new Guardian(3L, "Ismael", 123456789);
            guardianRepository.save(guardian1);
            guardianRepository.save(guardian2);

            Iterable<Guardian> guardians = guardianRepository.findAll();
            assertEquals(2, ((Collection<?>) guardians).size());
        }

        @Test
        void testDeleteById() {
            Guardian guardian = new Guardian(3L, "Ismael", 123456789);
            Guardian savedGuardian = guardianRepository.save(guardian);
            Long id = savedGuardian.getIdGuardian();

            guardianRepository.deleteById(id);

            Optional<Guardian> foundGuardian = guardianRepository.findById(id);
            assertFalse(foundGuardian.isPresent());
        }
    }