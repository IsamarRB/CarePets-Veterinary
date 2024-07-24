package com.CarePets.services;

import com.CarePets.models.Appointment;
import com.CarePets.models.Pet;
import com.CarePets.repositories.IAppointmentRepository;
import com.CarePets.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    IAppointmentRepository iAppointmentRepository;
    IPetRepository iPetRepository;
    public void createAppoinment(){}
    public ArrayList<Appointment> getAllAppointments(){
        return (ArrayList<Appointment>) iAppointmentRepository.findAll();
    }
    public Optional<Appointment> getAppointmentById(Long id){
        return iAppointmentRepository.findById(id);
    }
    public Appointment getAppointmentByName(Pet pet){
        String petName = pet.getName();
        return (Appointment) iAppointmentRepository.findByName(petName);
    }
    public Appointment getAppointmentByType(String typeConsult){
        return iAppointmentRepository.findByTypeConsult(typeConsult);
    }
    public Appointment getAppointmentByStatus(String status){
        return iAppointmentRepository.findByStatus(status);
    }
}
