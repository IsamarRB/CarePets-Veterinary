package com.CarePets.repositories;

import com.CarePets.models.Appointment;
import org.springframework.data.repository.CrudRepository;


public interface IAppointmentRepository extends CrudRepository<Appointment,Long> {

   Appointment findByTypeConsult(String typeConsult);
   Appointment findByStatus(String status);


}
