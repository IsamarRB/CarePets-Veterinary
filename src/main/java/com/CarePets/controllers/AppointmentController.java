package com.CarePets.controllers;

import com.CarePets.dto.CreateAppointmentRequest;
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
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;


    @PostMapping("/appointments")
    public Appointment createAppointment(@RequestBody CreateAppointmentRequest request) throws Exception {
        return appointmentService.createAppoinment(request);

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
