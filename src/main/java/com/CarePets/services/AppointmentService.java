package com.CarePets.services;

import com.CarePets.models.Appointment;
import com.CarePets.repositories.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
            IAppointmentRepository iAppointmentRepository;

    public void deleteAppointment(Long id){
        iAppointmentRepository.deleteById(id);
    }
}


