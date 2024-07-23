package com.CarePets.repositories;

import com.CarePets.models.Guardian;
import org.springframework.data.repository.CrudRepository;

public interface IGuardianRepository extends CrudRepository <Guardian,Long> {

}
