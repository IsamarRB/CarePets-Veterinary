package com.CarePets.services;

import com.CarePets.models.Appointment;
import com.CarePets.models.Pet;
import com.CarePets.repositories.IAppointmentRepository;
import com.CarePets.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    IAppointmentRepository iAppointmentRepository;
    IPetRepository iPetRepository;

    public Appointment createAppoinment(Pet pet, LocalDateTime localDateTime, Appointment newAppointment) throws Exception {
        if (availableDate(localDateTime)) {
            newAppointment.setPet(pet);
            return iAppointmentRepository.save(newAppointment);
        }else{
            throw new Exception("Appointment date and time are already taken.");
        }
    }

    public ArrayList<Appointment> getAllAppointments() {
        return (ArrayList<Appointment>) iAppointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return iAppointmentRepository.findById(id);
    }

    /*public Appointment getAppointmentByName(Pet pet) {
        String petName = pet.getName();
        return (Appointment) iAppointmentRepository.findByName(petName);
    }*/

    public Appointment getAppointmentByType(String typeConsult) {
        return iAppointmentRepository.findByTypeConsult(typeConsult);
    }

    public Appointment getAppointmentByStatus(String status) {
        return iAppointmentRepository.findByStatus(status);
    }

    public boolean availableDate(LocalDateTime localDateTime) {
        List<Appointment> appointments = (List<Appointment>) iAppointmentRepository.findAll();
        for (Appointment app : appointments) {
            if (app.getDateTime().equals(localDateTime)) {
                return false;
            }
        }
        return true;
    }
}
