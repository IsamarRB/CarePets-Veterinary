package com.CarePets.controllers;

import com.CarePets.models.Appointment;
import com.CarePets.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "*")

public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PutMapping(path = "/appointments/{id}")
    public void updateAppointment(@RequestBody Appointment appointment, @PathVariable Long id){
        appointmentService.updateAppointment(appointment, id);
    }
}
