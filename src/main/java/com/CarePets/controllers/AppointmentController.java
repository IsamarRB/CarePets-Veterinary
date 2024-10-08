package com.CarePets.controllers;

import com.CarePets.dto.CreateAppointmentRequest;
import com.CarePets.models.Appointment;
import com.CarePets.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/appointments/{id}")
    public Optional<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/appointments/type")
    public List<Appointment> getAppointmentByType(@RequestParam String typeConsult) {
        return appointmentService.getAppointmentByType(typeConsult);
    }

    @GetMapping("/appointments/status")
    public List<Appointment> getAppointmentByStatus(@RequestParam String status) {
        return appointmentService.getAppointmentByStatus(status);
    }
    @PutMapping(path = "/appointments/{id}")
    public void updateAppointment(@RequestBody Appointment appointment, @PathVariable Long id){
        appointmentService.updateAppointment(appointment, id);
    }

    @DeleteMapping(path = "/appointments/{id}")
    public void deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
    }

}
