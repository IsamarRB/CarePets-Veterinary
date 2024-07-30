package com.CarePets.services;

import com.CarePets.models.Appointment;
import com.CarePets.repositories.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    IAppointmentRepository iAppointmentRepository;
    public void updateAppointment(Appointment appointment, Long id){
        appointment.setIdAppointment(id);
        iAppointmentRepository.save(appointment);
    }
}
