package com.CarePets.repositories;

import com.CarePets.models.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentRepository extends CrudRepository<Appointment,Long> {
   Appointment findByName(String name);
   Appointment findByTypeConsult(String typeConsult);
   Appointment findByStatus(String status);
   boolean isDateAvailable(LocalDateTime localDateTime);

}
