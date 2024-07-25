package com.CarePets.controllers;

import com.CarePets.models.Appointment;
import com.CarePets.models.Pet;
import com.CarePets.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;


    @PostMapping("/appointments")
    public Appointment createAppointment(@RequestBody Appointment newAppointment){
        return appointmentService.createAppoinment(Pet pet, LocalDateTime localDateTime,newAppointment )

    }
    @GetMapping
    public List<Appointment> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }
    @GetMapping("/appointments/{id}")
    public Optional getAppointmentById(@PathVariable Long id){
        return (Optional)appointmentService.getAppointmentById(id);
    }
    @GetMapping("/appointments/{consultType}")
    public Appointment getAppointmentByType(@PathVariable String consultType){
        return appointmentService.getAppointmentByType(consultType);
    }
    /*@GetMapping("/appointments/{petName}")
    public Appointment getAppointmentByPetName(@PathVariable Pet pet){
        return appointmentService.getAppointmentByName(pet);
    }*/

    @GetMapping("/appointments/{status}")
    public Appointment getAppointmentByStatus(@PathVariable String status){
        return appointmentService.getAppointmentByStatus(status);
    }
}
