package com.CarePets.repositories;

import com.CarePets.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface IPetRepository extends CrudRepository<Pet, Long> {
}
