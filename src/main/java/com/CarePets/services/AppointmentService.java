package com.CarePets.services;

import com.CarePets.dto.CreateAppointmentDTO;
import com.CarePets.models.Appointment;
import com.CarePets.repositories.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CarePets.models.Pet;
import com.CarePets.repositories.IPetRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    IAppointmentRepository iAppointmentRepository;
    public void updateAppointment(Appointment appointment, Long id){
        appointment.setIdAppointment(id);
        iAppointmentRepository.save(appointment);
    }
    @Autowired
    IPetRepository iPetRepository;

    public Appointment createAppoinment(CreateAppointmentDTO request) throws Exception {
        Optional<Pet> optionalPet = iPetRepository.findById(request.getIdPet());
        if (!optionalPet.isPresent()) {
            throw new Exception("Pet not found");
        }

        Pet pet = optionalPet.get();
        if (availableDate(request.getDateTime())) {
            Appointment newAppointment = new Appointment();
            newAppointment.setPet(pet);
            newAppointment.setDateTime(request.getDateTime());
            newAppointment.setTypeConsult(request.getTypeConsult());
            newAppointment.setReason(request.getReason());
            newAppointment.setStatus(request.getStatus());
            return iAppointmentRepository.save(newAppointment);
        } else {
            throw new Exception("Appointment date and time are already taken.");
        }
    }

    public ArrayList<Appointment> getAllAppointments() {
        return (ArrayList<Appointment>) iAppointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return iAppointmentRepository.findById(id);
    }


    public List<Appointment> getAppointmentByType(String typeConsult) {
        return getAllAppointments()
                .stream()
                .filter(c-> typeConsult.equals(c.getTypeConsult()))
                .collect(Collectors.toList());

    }

    public List<Appointment> getAppointmentByStatus(String status) {

        return getAllAppointments()
                .stream()
                .filter(c -> status.equals(c.getStatus()))
                .collect(Collectors.toList());
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
