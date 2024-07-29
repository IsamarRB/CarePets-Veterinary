package com.CarePets.repositories;

import com.CarePets.models.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface IAppointmentRepository extends CrudRepository<Appointment,Long> {

   List<Appointment> findByTypeConsult(String typeConsult);
   List<Appointment> findByStatus(String status);


}
